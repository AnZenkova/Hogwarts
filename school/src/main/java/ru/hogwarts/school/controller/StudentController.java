package ru.hogwarts.school.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("student")
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity getStudent(@PathVariable Long id) {
        Student student = studentService.findStudent(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @PutMapping
    public ResponseEntity editStudent(@RequestBody Student student) {
        Student foundStudent = studentService.editStudent(student);
        return ResponseEntity.ok(foundStudent);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/age")
    public ResponseEntity filterStudent(@RequestParam int age) {
        if (age == 0) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(studentService.filterStudent(age));
    }

    @GetMapping("{min}, {max}")
    public Collection<Student> findByAgeBetween(@PathVariable int min,
                                                @PathVariable int max) {
        return studentService.findByAgeBetween(min, max);
    }

    @GetMapping("/faculty")
    public ResponseEntity findFaculty(@RequestParam Long studentId) {
        return ResponseEntity.ok(studentService.getFaculty(studentId));
    }

    @GetMapping("/quantity")
    public ResponseEntity getNumberOfStudent() {
        return ResponseEntity.ok(studentService.getNumberOfStudents());
    }

    @GetMapping("/middleAge")
    public ResponseEntity getMiddleAgeOfStudents() {
        return ResponseEntity.ok(studentService.getMiddleAgeOfStudents());
    }

    @GetMapping("/lastStudents")
    public Collection<Student> getLastFiveStudent() {
        return studentService.getLastFiveStudent();
    }

    @GetMapping("/get{name}")
    public ResponseEntity<List<Student>> getStudentByName(@PathVariable String name) {
        List<Student> students = studentService.getStudentByName(name);
        return ResponseEntity.ok(students);
    }
}
