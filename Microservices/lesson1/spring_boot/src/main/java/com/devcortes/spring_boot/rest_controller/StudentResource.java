package com.devcortes.spring_boot.rest_controller;

import com.devcortes.spring_boot.entity.Student;
import com.devcortes.spring_boot.exception.StudentNotFoundException;
import com.devcortes.spring_boot.service.DaoStudentRepository;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.ControllerLinkBuilder;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentResource {

    private DaoStudentRepository daoStudentRepository;

    public StudentResource(DaoStudentRepository daoStudentRepository) {
        this.daoStudentRepository = daoStudentRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Student>> getById(@PathVariable Long id){
        Student student = daoStudentRepository.findById(id);
        if (student.getId() == null) {
            throw new StudentNotFoundException(String.format("Not found id - %d", id));
        }
        EntityModel<Student> model = new EntityModel<>(student);
        //WebMvcLinkBuilder webMvcLinkBuilder = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllStudents());
        //model.add(webMvcLinkBuilder.withRel("/all-students"));
        WebMvcLinkBuilder webMvcLinkBuilder = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getById(id));
        model.add(webMvcLinkBuilder.withSelfRel());
        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity save(@Valid @RequestBody Student student){
        Student save = daoStudentRepository.save(student);
        URI uriComponents = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(save.getId()).toUri();
        return ResponseEntity.created(uriComponents).build();
    }

    @GetMapping("/all-students")
    public ResponseEntity<List<Student>> getAllStudents(){
        List<Student> students = daoStudentRepository.findAll();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }
}
