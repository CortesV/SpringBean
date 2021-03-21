package com.devcortes.demo.beanscopes;

import com.devcortes.demo.beans.Greeting;
import com.devcortes.demo.beans.IGreeting;

public class BeanWithDependency {

    IGreeting greeting;

    public BeanWithDependency(IGreeting greeting) {
        this.greeting = greeting;
    }

    public void mesage(){
        greeting.sayGreeting();
    }
}
