package com.devcortes.springdata.mysql.components.dao;

import com.devcortes.springdata.mysql.components.entity.Student;
import com.devcortes.springdata.mysql.components.interfaces.IStudent;
import com.devcortes.springdata.mysql.components.repository.StudentMySQLRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentMySQLDao implements IStudent {

    @Autowired
    private StudentMySQLRepository studentMySQLRepository;

    @Override
    public Student findById(Integer id) {
        return studentMySQLRepository.findById(id);
    }

    @Override
    public void save(Student student) {
        studentMySQLRepository.save(student);
    }

    @Override
    public List<Student> findAll() {
        return (List<Student>) studentMySQLRepository.findAll();
    }
}
