package com.devcortes.demo.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;

@Configuration
@ComponentScan("com.devcortes.demo")
public class ConfigAnnotation {
    @Bean(name = {"greeting", "greetingBean"})
    @Description("Bean for saying hello")
    public Greeting greetingService() {
        return new Greeting();
    }

    @Bean(name = {"lifecycle"}, initMethod = "start", destroyMethod = "stop")
    @Description("Bean for lifecycle")
    public LifecycleProcessor lifecycleProcessor() {
        return new LifecycleProcessor();
    }


}
