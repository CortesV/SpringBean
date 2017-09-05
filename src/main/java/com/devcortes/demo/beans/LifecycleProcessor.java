package com.devcortes.demo.beans;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class LifecycleProcessor implements Lifecycle, InitializingBean, DisposableBean {
    @Override
    public void start() {
        System.out.println("start");
    }

    @Override
    public void stop() {
        System.out.println("stop");
    }

    @Override
    public boolean isRunning() {
        System.out.println("running");
        return false;
    }

    public LifecycleProcessor() {
        System.out.println("Constructor");
    }

    @PostConstruct
    public void initAnnotation(){
        System.out.println("PostConstruct @PostConstruct");
    }

    @PreDestroy
    public void destroyAnnotation(){
        System.out.println("Destroy @PreDestroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("destroy");
    }
}
