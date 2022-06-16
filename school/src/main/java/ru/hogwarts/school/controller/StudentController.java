package ru.hogwarts.school.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;
import java.util.List;
import java.util.OptionalDouble;

@RestController
@RequestMapping("student")
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // Добавляем студента в базу данных
    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    // Получаем студента по его id
    @GetMapping("/id/{id}")
    public ResponseEntity getStudent(@PathVariable Long id) {
        Student student = studentService.findStudent(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    // Вносим изменения в информацию о студенте
    @PutMapping
    public ResponseEntity editStudent(@RequestBody Student student) {
        Student foundStudent = studentService.editStudent(student);
        return ResponseEntity.ok(foundStudent);
    }

    // Удаляем студента по его id
    @DeleteMapping("{id}")
    public ResponseEntity deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    // Получаем студентов по их возрасту
    @GetMapping("/age")
    public ResponseEntity filterStudent(@RequestParam int age) {
        if (age == 0) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(studentService.filterStudent(age));
    }

    // Получаем список студентов в промежутке передаваемого возроста
    @GetMapping("{min}, {max}")
    public Collection<Student> findByAgeBetween(@PathVariable int min,
                                                @PathVariable int max) {
        return studentService.findByAgeBetween(min, max);
    }

    // Получаем факультет по id студента
    @GetMapping("/faculty")
    public ResponseEntity findFaculty(@RequestParam Long studentId) {
        return ResponseEntity.ok(studentService.getFaculty(studentId));
    }

    // Получаем количество студентов
    @GetMapping("/quantity")
    public ResponseEntity getNumberOfStudent() {
        return ResponseEntity.ok(studentService.getNumberOfStudents());
    }

    // Получаем средний возраст студентов
    @GetMapping("/middleAge")
    public ResponseEntity getMiddleAgeOfStudents() {
        return ResponseEntity.ok(studentService.getMiddleAgeOfStudents());
    }

    // Получаем пять последних студентов
    @GetMapping("/lastStudents")
    public Collection<Student> getLastFiveStudent() {
        return studentService.getLastFiveStudent();
    }


    // Получаем студентов по имени
    @GetMapping("/get{name}")
    public ResponseEntity<List<Student>> getStudentByName(@PathVariable String name) {
        List<Student> students = studentService.getStudentByName(name);
        return ResponseEntity.ok(students);
    }

    // Получаем студентов у которых имена начинаются с буквы А через stream
    @GetMapping("/findAllA")
    public ResponseEntity findAll() {
        return ResponseEntity.ok(studentService.findAllA());
    }

    // Получаем средний возраст студентов
    @GetMapping("/averageAgeAllStudent")
    public ResponseEntity<OptionalDouble> averageAgeAllStudent() {
        return ResponseEntity.ok(studentService.averageAgeAllStudent());
    }

    // Полчаем студентов в консоль с помощью потоков
    @GetMapping("/getAllStudentInConsole")
    public void getAllStudentInConsole() {
        studentService.getAllStudentInConsole();
    }

    // Получаем студентов в консоль с помощью синхронизированных потоков
    @GetMapping("/getAllStudentInConsole2")
    public void getAllStudentInConsole2() {
        studentService.getAllStudentInConsole2();
    }
}
