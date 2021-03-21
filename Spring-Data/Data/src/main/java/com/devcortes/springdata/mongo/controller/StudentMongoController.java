package com.devcortes.springdata.mongo.controller;

import com.devcortes.springdata.mongo.components.entity.Student;
import com.devcortes.springdata.mongo.components.enums.Gender;
import com.devcortes.springdata.mongo.service.StudentMongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/student/mongo")
public class StudentMongoController {

    @Autowired
    private StudentMongoService studentMongoService;

    @RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json")
    public void saveStudent(@RequestBody Student student) {
        studentMongoService.save(student);
    }


    @RequestMapping(value = "data/{id}", method = RequestMethod.GET, produces = "application/json")
    public Student findStudent(@PathVariable("id") String id) {
        return studentMongoService.findById(id);
    }

    @RequestMapping(value = "data/list", method = RequestMethod.GET, produces = "application/json")
    public List<Student> findAllStudentsData() {
        Student student = new Student("John Moriwwwwson", Gender.MALE.name(), 2);
        studentMongoService.save(student);
        Student student2 = new Student("John KKK", Gender.MALE.name(), 21);
        studentMongoService.save(student2);
        return studentMongoService.findAll();
    }

}
