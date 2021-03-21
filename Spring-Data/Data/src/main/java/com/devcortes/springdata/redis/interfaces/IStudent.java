package com.devcortes.springdata.redis.interfaces;

import com.devcortes.springdata.redis.model.Student;

import java.util.Map;

public interface IStudent {

    public void saveStudent(Student person);
    public void updateStudent(Student student);
    public Student findStudent(String id);
    public Map<Object, Object> findAllStudents();
    public void deleteStudent(String id);
}
