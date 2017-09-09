package com.devcortes.demo.beans;

public class Greeting implements IGreeting{
    @Override
    public String sayGreeting() {
        return "Hello Cortes singleton";
    }
}
