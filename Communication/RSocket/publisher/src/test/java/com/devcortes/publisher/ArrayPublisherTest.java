package com.devcortes.publisher;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.reactivestreams.tck.PublisherVerification;
import org.reactivestreams.tck.TestEnvironment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.LongStream;

import static org.testng.Assert.*;

public class ArrayPublisherTest extends PublisherVerification<Long> {

    static Long[] generate(long num) {
        return LongStream.range(0, num >= Integer.MAX_VALUE ? 1_000_000 : num).boxed().toArray(Long[]::new);
    }

    public ArrayPublisherTest() {
        super(new TestEnvironment());
    }

    @Override
    public Publisher<Long> createPublisher(long elements) {
        return new ArrayPublisher<>(generate(elements));
    }

    @Override
    public Publisher<Long> createFailedPublisher() {
        return null;
    }

    @Test
    public void signalsShouldBeEmittedTheRightOrder() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        ArrayList<Long> collected = new ArrayList<>();
        ArrayList<Integer> order = new ArrayList<>();
        long toRequest = 5L;
        Long[] array = generate(toRequest);
        ArrayPublisher<Long> publisher = new ArrayPublisher<>(array);

        publisher.subscribe(new Subscriber<Long>() {
            @Override
            public void onSubscribe(Subscription s) {
                order.add(0);
                s.request(toRequest);
            }

            @Override
            public void onNext(Long aLong) {
                collected.add(aLong);
                if (!order.contains(1)) {
                    order.add(1);
                }
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {
                order.add(2);
                countDownLatch.countDown();
            }
        });

        countDownLatch.await(1, TimeUnit.SECONDS);
        Assertions.assertEquals(order, Arrays.asList(0, 1, 2));
        Assertions.assertEquals(collected, Arrays.asList(array));
    }

    @Test
    public void mustSupportBackpressureControl() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        ArrayList<Long> collected = new ArrayList<>();
        long toRequest = 5L;
        Long[] array = generate(toRequest);
        ArrayPublisher<Long> publisher = new ArrayPublisher<>(array);
        Subscription[] subscription = new Subscription[1];

        publisher.subscribe(new Subscriber<Long>() {
            @Override
            public void onSubscribe(Subscription s) {
                subscription[0] = s;
            }

            @Override
            public void onNext(Long aLong) {
                collected.add(aLong);
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {
                countDownLatch.countDown();
            }
        });

        Assertions.assertEquals(collected, Collections.emptyList());

        subscription[0].request(1);
        Assertions.assertEquals(collected, Arrays.asList(0L));

        subscription[0].request(1);
        Assertions.assertEquals(collected, Arrays.asList(0L, 1L));

        subscription[0].request(2);
        Assertions.assertEquals(collected, Arrays.asList(0L, 1L, 2L, 3L));

        subscription[0].request(20);

        countDownLatch.await(1, TimeUnit.SECONDS);

        Assertions.assertEquals(collected, Arrays.asList(array));
    }

    @Test
    public void mustSendNPENormally() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Long[] array = new Long[]{null};
        AtomicReference<Throwable> error = new AtomicReference<>();
        ArrayPublisher<Long> publisher = new ArrayPublisher<>(array);

        publisher.subscribe(new Subscriber<Long>() {
            @Override
            public void onSubscribe(Subscription s) {
                s.request(4);
            }

            @Override
            public void onNext(Long aLong) {

            }

            @Override
            public void onError(Throwable t) {
                error.set(t);
                countDownLatch.countDown();
            }

            @Override
            public void onComplete() {

            }
        });

        countDownLatch.await(1, TimeUnit.SECONDS);
        Assertions.assertTrue(error.get() instanceof NullPointerException);
    }

    @Test
    public void shouldNotDieInStackOverflow() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        ArrayList<Long> collected = new ArrayList<>();
        long toRequest = 5L;
        Long[] array = generate(toRequest);
        ArrayPublisher<Long> publisher = new ArrayPublisher<>(array);

        publisher.subscribe(new Subscriber<Long>() {
            private Subscription s;

            @Override
            public void onSubscribe(Subscription s) {
                this.s = s;
                s.request(1);
            }

            @Override
            public void onNext(Long aLong) {
                collected.add(aLong);
                s.request(1);
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {
                countDownLatch.countDown();
            }
        });

        countDownLatch.await(5, TimeUnit.SECONDS);
        Assertions.assertEquals(collected, Arrays.asList(array));
    }

    @Test
    public void shouldBePossibleToCancelSubscription() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        ArrayList<Long> collected = new ArrayList<>();
        long toRequest = 1000L;
        Long[] array = generate(toRequest);
        ArrayPublisher<Long> publisher = new ArrayPublisher<>(array);

        publisher.subscribe(new Subscriber<Long>() {

            @Override
            public void onSubscribe(Subscription s) {
                s.cancel();
                s.request(toRequest);
            }

            @Override
            public void onNext(Long aLong) {
                collected.add(aLong);
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {
                countDownLatch.countDown();
            }
        });

        countDownLatch.await(5, TimeUnit.SECONDS);
        Assertions.assertEquals(collected, Collections.emptyList());
    }

    @Test
    public void multithreadingTest() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        ArrayList<Long> collected = new ArrayList<>();
        final int toRequest = 5000;
        Long[] array = generate(toRequest);
        ArrayPublisher<Long> publisher = new ArrayPublisher<>(array);

        publisher.subscribe(new Subscriber<Long>() {

            private Subscription subscription;

            @Override
            public void onSubscribe(Subscription s) {
                this.subscription = s;
                for (int i = 0; i < toRequest; i++) {
                    ForkJoinPool.commonPool().execute(() -> s.request(1));
                }
            }

            @Override
            public void onNext(Long aLong) {
                collected.add(aLong);
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {
                countDownLatch.countDown();
            }
        });

        countDownLatch.await(5, TimeUnit.SECONDS);
        Assertions.assertEquals(Arrays.asList(array), collected);
    }
}