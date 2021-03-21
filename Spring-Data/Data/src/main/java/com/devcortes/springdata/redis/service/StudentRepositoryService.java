package com.devcortes.springdata.redis.service;

import com.devcortes.springdata.redis.interfaces.IStudent;
import com.devcortes.springdata.redis.interfaces.StudentRedisRepository;
import com.devcortes.springdata.redis.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.hash.HashMapper;
import org.springframework.data.redis.hash.ObjectHashMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudentRepositoryService {

    @Autowired
    private StringRedisConnection stringRedisConnection;

    @Autowired
    private ObjectHashMapper objectHashMapper;

    @Autowired
    private HashMapper<Object, byte[], byte[]> mapper;

    @Autowired
    private StudentRedisRepository studentRedisRepository;

    @Autowired
    private IStudent iStudent;

    public Student manipulateHashAndReadItBack(){
        Student student = new Student("Eng2015001", "John Doe", Student.Gender.MALE, 1);
        Map<String, String> rawHash = mapper.toHash(student).entrySet().stream().collect(Collectors.toMap(e -> new String(e.getKey()), e -> new String(e.getValue())));
        System.out.println(rawHash);
        stringRedisConnection.hMSet("student:1", rawHash);
        stringRedisConnection.hSet("student:1", "name", "grade");
        Student fromHash = (Student) mapper.fromHash(stringRedisConnection.hGetAll("student:1".getBytes()));
        return fromHash;
    }

    public void saveStudent(Student student) {
        studentRedisRepository.save(student);
    }

    public void updateStudent(Student student) {
        iStudent.updateStudent(student);
    }

    public Student findStudent(String id) {
        return studentRedisRepository.findOne(id);
    }

    public Iterable<Student> findAllStudents() {
        return studentRedisRepository.findAll();
    }

    public void deleteStudent(String id) {
        studentRedisRepository.delete(id);
    }

    public List<Student> findAllStudents(String name, Integer grade) {
        return studentRedisRepository.findByNameAndGrade(name, grade);
    }
}
