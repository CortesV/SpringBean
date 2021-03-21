package com.devcortes.springdata.mongo.components.dao;

import com.devcortes.springdata.mongo.components.entity.Student;
import com.devcortes.springdata.mongo.components.interfaces.IStudent;
import com.devcortes.springdata.mongo.components.repository.StudentMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentMongoDao implements IStudent {

    @Autowired
    private StudentMongoRepository studentMongoRepository;

    @Override
    public Student findById(String id) {
        return studentMongoRepository.findById(id);
    }

    @Override
    public void save(Student student) {
        studentMongoRepository.save(student);
    }

    @Override
    public List<Student> findAll() {
        return (List<Student>) studentMongoRepository.findAll();
    }
}
