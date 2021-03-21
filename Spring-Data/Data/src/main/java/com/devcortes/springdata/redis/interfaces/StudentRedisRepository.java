package com.devcortes.springdata.redis.interfaces;

import com.devcortes.springdata.redis.model.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRedisRepository extends CrudRepository<Student, String> {
    public List<Student> findByNameAndGrade(String name, Integer grade);
}
