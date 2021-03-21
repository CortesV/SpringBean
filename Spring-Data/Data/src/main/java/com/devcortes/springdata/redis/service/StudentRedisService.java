package com.devcortes.springdata.redis.service;

import com.devcortes.springdata.redis.interfaces.IStudent;
import com.devcortes.springdata.redis.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class StudentRedisService {
    @Autowired
    private IStudent iStudent;

    public void saveStudent(Student student) {
        iStudent.saveStudent(student);
    }

    public void updateStudent(Student student) {
        iStudent.updateStudent(student);
    }

    public Student findStudent(String id) {
        return iStudent.findStudent(id);
    }

    public Map<Object, Object> findAllStudents() {
        return iStudent.findAllStudents();
    }

    public void deleteStudent(String id) {
        iStudent.deleteStudent(id);
    }
}
