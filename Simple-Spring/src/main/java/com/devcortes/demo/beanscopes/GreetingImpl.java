package com.devcortes.demo.beanscopes;

import com.devcortes.demo.beans.IGreeting;

public class GreetingImpl implements IGreeting {
    @Override
    public String sayGreeting() {
        return "Hello I am prorotype";
    }
}
