package com.devcortes.springdata.redis.dao;

import com.devcortes.springdata.redis.cachekeys.RedisKey;
import com.devcortes.springdata.redis.interfaces.IStudent;
import com.devcortes.springdata.redis.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Map;

@Repository
public class StudentRedisDao implements IStudent {

    private RedisTemplate<String, Student> jedisTemplate;
    private HashOperations hashOps;

    @Autowired
    public StudentRedisDao(RedisTemplate jedisTemplate) {
        this.jedisTemplate = jedisTemplate;
    }

    @PostConstruct
    private void init() {
        hashOps = jedisTemplate.opsForHash();
    }

    public void saveStudent(Student student) {
        hashOps.put(RedisKey.Student, student.getId(), student);
    }

    public void updateStudent(Student student) {
        hashOps.put(RedisKey.Student, student.getId(), student);
    }

    public Student findStudent(String id) {
        return (Student) hashOps.get(RedisKey.Student, id);
    }

    public Map<Object, Object> findAllStudents() {
        return hashOps.entries(RedisKey.Student);
    }

    public void deleteStudent(String id) {
        hashOps.delete(RedisKey.Student, id);
    }
}
