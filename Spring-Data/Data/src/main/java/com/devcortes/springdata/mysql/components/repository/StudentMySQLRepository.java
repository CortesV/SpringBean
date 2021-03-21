package com.devcortes.springdata.mysql.components.repository;

import com.devcortes.springdata.mysql.components.entity.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentMySQLRepository extends CrudRepository<Student, Long>{

    Student findById(Integer id);
}
