package com.devcortes.springdata.redis.controllers;

import com.devcortes.springdata.redis.model.Student;
import com.devcortes.springdata.redis.service.StudentRepositoryService;
import com.devcortes.springdata.redis.service.StudentRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/student/redis")
public class StudentRedisController {

    @Autowired
    private StudentRedisService studentRedisService;

    @Autowired
    private StudentRepositoryService studentRepositoryService;

    @RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json")
    public void saveStudent(@RequestBody Student student) {
        studentRedisService.saveStudent(student);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, produces = "application/json")
    public void updateStudent(@RequestBody Student student) {
        studentRedisService.updateStudent(student);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = "application/json")
    public Student findStudent(@PathVariable("id") String id) {
        Student student = new Student("Eng2015001", "John Snoy", Student.Gender.MALE, 1);
        studentRedisService.updateStudent(student);
        Student student2 = new Student("Eng2015002", "John Morr", Student.Gender.MALE, 2);
        studentRedisService.saveStudent(student2);
        return studentRedisService.findStudent(id);
    }

    @RequestMapping(value = "list", method = RequestMethod.GET, produces = "application/json")
    public Map<Object, Object> findAllStudents() {
        return studentRedisService.findAllStudents();
    }

    @RequestMapping(value = "", method = RequestMethod.DELETE, produces = "application/json")
    public void deleteStudent(String id) {
        studentRedisService.deleteStudent(id);
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public Student find() {
        return studentRepositoryService.manipulateHashAndReadItBack();
    }

    @RequestMapping(value = "data/", method = RequestMethod.POST, produces = "application/json")
    public void saveStudentData(@RequestBody Student student) {
        studentRedisService.saveStudent(student);
    }

    @RequestMapping(value = "data/", method = RequestMethod.PUT, produces = "application/json")
    public void updateStudentData(@RequestBody Student student) {
        studentRedisService.updateStudent(student);
    }

    @RequestMapping(value = "data/{id}", method = RequestMethod.GET, produces = "application/json")
    public Student findStudentData(@PathVariable("id") String id) {
        return studentRedisService.findStudent(id);
    }

    @RequestMapping(value = "data/list", method = RequestMethod.GET, produces = "application/json")
    public Map<Object, Object> findAllStudentsData() {
        Student student = new Student("Eng2015004", "John Moriwwwwson", Student.Gender.MALE, 2);
        studentRedisService.saveStudent(student);
        Student student2 = new Student("Eng2015003", "John KKK", Student.Gender.MALE, 2);
        studentRedisService.updateStudent(student2);
        return studentRedisService.findAllStudents();
    }

    @RequestMapping(value = "data/", method = RequestMethod.DELETE, produces = "application/json")
    public void deleteStudentData(String id) {
        studentRedisService.deleteStudent(id);
    }
}
