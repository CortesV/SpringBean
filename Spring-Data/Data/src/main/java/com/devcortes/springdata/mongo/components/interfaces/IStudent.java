package com.devcortes.springdata.mongo.components.interfaces;

import com.devcortes.springdata.mongo.components.entity.Student;

import java.util.List;

public interface IStudent {

    Student findById(String id);
    void save(Student student);
    List<Student> findAll();
}
