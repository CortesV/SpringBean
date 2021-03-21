package com.devcortes.demo.enviroment;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Conditional;

import javax.sql.DataSource;

public class TestBean {
    private String name;

    @Value("#{systemProperties['os.arch'].equals('x86') ? winDataSource : unixDataSource}")
    private DataSource datasource;

    public TestBean() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
