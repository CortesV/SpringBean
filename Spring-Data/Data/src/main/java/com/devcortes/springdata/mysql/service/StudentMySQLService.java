package com.devcortes.springdata.mysql.service;

import com.devcortes.springdata.mysql.components.entity.Student;
import com.devcortes.springdata.mysql.components.interfaces.IStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentMySQLService {

    @Autowired
    private IStudent dao;

    public Student findById(Integer id) {
        return dao.findById(id);
    }

    public void save(Student student) {
        dao.save(student);
    }

    public List<Student> findAll() {
        return dao.findAll();
    }
}
