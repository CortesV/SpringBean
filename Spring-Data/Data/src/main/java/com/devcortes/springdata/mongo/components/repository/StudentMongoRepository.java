package com.devcortes.springdata.mongo.components.repository;

import com.devcortes.springdata.mongo.components.entity.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

public interface StudentMongoRepository extends MongoRepository<Student, String>{

    Student findById(String id);
}
