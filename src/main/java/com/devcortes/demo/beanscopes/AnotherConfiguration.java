package com.devcortes.demo.beanscopes;

import com.devcortes.demo.beans.IGreeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
public class AnotherConfiguration {

    @Autowired
    IGreeting greetingService;

    @Bean
    BeanWithDependency beanWithDependency() {
        return new BeanWithDependency(greetingService);
    }
}
