package com.devcortes.demo.screensaver;

import com.devcortes.demo.beans.ConfigAnnotation;
import com.devcortes.demo.beans.Greeting;
import com.devcortes.demo.beans.LifecycleProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.context.support.AbstractApplicationContext;

import java.awt.*;
import java.util.Random;

@Configuration
@ComponentScan("com.devcortes.demo")
public class Config {

    @Bean
    @Scope("periodical")
    public Color color(){
        Random random = new Random();
        return new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
    }

    @Bean
    public ColorFrame frame(){
        return  new ColorFrame() {
            @Override
            protected Color getColor() {
                return color();
            }
        };
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Starting annotation configuration...");
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(Config.class);
        AbstractApplicationContext applicationContext = new AnnotationConfigApplicationContext(ConfigAnnotation.class);
        Greeting greetingService = annotationConfigApplicationContext.getBean(Greeting.class);
        System.out.println(greetingService.sayGreeting());
        LifecycleProcessor lifecycleProcessor = applicationContext.getBean(LifecycleProcessor.class);
        applicationContext.registerShutdownHook();
        /*while(true){
            annotationConfigApplicationContext.getBean(ColorFrame.class).showOnRandomPlace();
            Thread.sleep(100);
        }*/
    }
}
