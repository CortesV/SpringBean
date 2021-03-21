package com.devcortes.springdata.mysql.components.interfaces;

import com.devcortes.springdata.mysql.components.entity.Student;

import java.util.List;

public interface IStudent {

    Student findById(Integer id);
    void save(Student student);
    List<Student> findAll();
}
