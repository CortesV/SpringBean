package com.devcortes.springdata.mongo.service;

import com.devcortes.springdata.mongo.components.entity.Student;
import com.devcortes.springdata.mongo.components.interfaces.IStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentMongoService {

    @Autowired
    private IStudent dao;

    public Student findById(String id) {
        return dao.findById(id);
    }

    public void save(Student student) {
        dao.save(student);
    }

    public List<Student> findAll() {
        return dao.findAll();
    }
}
