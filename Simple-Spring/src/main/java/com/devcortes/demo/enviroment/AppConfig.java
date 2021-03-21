package com.devcortes.demo.enviroment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:/com/${my.placeholder:default/path}/app.properties")
public class AppConfig {
    @Autowired
    Environment env;

    @Bean
    public TestBean testBean() {
        TestBean testBean = new TestBean();
        testBean.setName(env.getProperty("testbean.name"));
        return testBean;
    }

    @Bean(name="emailerService")
    @Conditional(WindowsCondition.class)
    public ITest windowsEmailerService(){
        return new WindowsOS();
    }

    @Bean(name="emailerService")
    @Conditional(LinuxCondition.class)
    public ITest linuxEmailerService(){
        return new LinuxOS();
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.getEnvironment().setActiveProfiles("Linux");
        context.scan("com.intertech");
        context.refresh();
    }
}
