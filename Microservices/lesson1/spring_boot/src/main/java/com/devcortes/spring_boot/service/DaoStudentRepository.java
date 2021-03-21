package com.devcortes.spring_boot.service;

import com.devcortes.spring_boot.entity.Student;
import com.devcortes.spring_boot.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class DaoStudentRepository {

    private final StudentRepository studentRepository;

    public DaoStudentRepository(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student findById(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        return student.orElse(new Student());
    }

    public List<Student> findAll() {
        Iterable<Student> students = studentRepository.findAll();
        return StreamSupport.stream(students.spliterator(), false)
                .collect(Collectors.toList());
    }

    public Student save(Student student) {
        Student save = studentRepository.save(student);
        return save;
    }

    public void delete(Long id) {
        studentRepository.deleteById(id);
    }
}
