package com.devcortes.demo.beanscopes;

import com.devcortes.demo.beans.Greeting;
import com.devcortes.demo.beans.IGreeting;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan
@Import(AnotherConfiguration.class)
public class BeanScopeConfiguration {

    @Bean
    @Scope("prototype")
    public Object asyncCommand() {
        return new Object();
    }

    @Bean
    public CommandManager commandManager() {
        return new CommandManager() {
            protected Object createCommand() {
                return asyncCommand();
            }
        };
    }

    @Bean
    IGreeting greetingService() {
        return new GreetingImpl();
    }

    public static void main(String[] args) {
        System.out.println("Starting configuration...");
        ApplicationContext context = new AnnotationConfigApplicationContext(BeanScopeConfiguration.class);
        IGreeting greetingService = context.getBean(IGreeting.class);
        BeanWithDependency withDependency = context.getBean(BeanWithDependency.class);
        System.out.println(greetingService.sayGreeting()); // "Greeting, user!"
        withDependency.mesage(); // "Some text!"
    }
}
