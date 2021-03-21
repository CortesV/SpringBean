package com.devcortes.springdata.mysql.controller;

import com.devcortes.springdata.mysql.components.entity.Student;
import com.devcortes.springdata.mysql.components.enums.Gender;
import com.devcortes.springdata.mysql.service.StudentMySQLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/student/mysql")
public class StudentMySQLController {

    @Autowired
    private StudentMySQLService studentMySQLService;

    @RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json")
    public void saveStudent(@RequestBody Student student) {
        studentMySQLService.save(student);
    }


    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = "application/json")
    public Student findStudent(@PathVariable("id") Integer id) {
        return studentMySQLService.findById(id);
    }

    @RequestMapping(value = "data/list", method = RequestMethod.GET, produces = "application/json")
    public List<Student> findAllStudentsData() {
        Student student = new Student("John Moriwwwwson", Gender.MALE.name(), 2);
        studentMySQLService.save(student);
        Student student2 = new Student("John KKK", Gender.MALE.name(), 21);
        studentMySQLService.save(student2);
        return studentMySQLService.findAll();
    }

}
