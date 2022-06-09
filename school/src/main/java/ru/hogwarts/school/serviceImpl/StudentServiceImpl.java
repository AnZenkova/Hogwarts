package ru.hogwarts.school.serviceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;
import ru.hogwarts.school.service.StudentService;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    Logger logger = LoggerFactory.getLogger(FacultyServiceImpl.class);

    StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        logger.debug("The method is called createStudent from the student class");
        return studentRepository.save(student);
    }

    public Student findStudent(Long id) {
        logger.debug("The method is called findStudent from the student class");
        return studentRepository.getById(id);
    }

    public Student editStudent(Student student) {
        logger.debug("The method is called editStudent from the student class");
        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        logger.debug("The method is called save from the student class");
        studentRepository.deleteById(id);
    }

    public List<Student> getAll() {
        logger.debug("The method is called deleteStudent from the student class");
        return studentRepository.findAll();
    }

    public Set<Student> filterStudent(int age) {
        logger.debug("The method is called filterStudent from the student class");
        List<Student> students = getAll();
        Set<Student> filter = students.stream()
                .filter(s -> s.getAge() == age)
                .collect(Collectors.toSet());
        return filter;
    }

    public Collection<Student> findByAgeBetween(int min, int max) {
        logger.debug("The method is called findByAgeBetween from the student class");
        return studentRepository.findByAgeBetween(min, max);
    }

    public String getFaculty(Long studentId) {
        logger.debug("The method is called getFaculty from the student class");
        Student student = findStudent(studentId);
        Faculty faculty = student.getFaculty();
        return faculty.getName();
    }

    public Integer getNumberOfStudents() {
        logger.debug("The method is called getNumberOfStudents from the student class");
        return studentRepository.getNumberOfStudents();
    }

    public Integer getMiddleAgeOfStudents() {
        logger.debug("The method is called getMiddleAgeOfStudents from the student class");
        return studentRepository.getMiddleAgeOfStudents();
    }

    public List<Student> getLastFiveStudent() {
        logger.debug("The method is called getLastFiveStudent from the student class");
        return studentRepository.getLastFiveStudent();
    }

    public List<Student> getStudentByName(String name) {
        logger.debug("The method is called getStudentByName from the student class");
        return studentRepository.getStudentByName(name);
    }

    public List<String> findAllA() {

        List<Student> studentList = studentRepository.findAll();
        List<String> students = studentList.stream()
                .map(student -> student.getName().substring(0, 1).toUpperCase() + student.getName().substring(1))
                .filter(student -> student.charAt(0) == 'A')
                .sorted()
                .collect(Collectors.toList());
        return students;
    }

    public OptionalDouble averageAgeAllStudent() {
        List<Student> studentList = studentRepository.findAll();
        OptionalDouble average = studentList.stream()
                .mapToInt(Student::getAge)
                .average();
        return average;
    }

    public void getAllStudentInConsole() {

        List<Student> students = studentRepository.findAll();

        printStudent(students.get(0));
        printStudent(students.get(1));

        Thread thread1 = new Thread(() ->{

            printStudent(students.get(2));
            printStudent(students.get(3));

        });
        thread1.start();

        Thread thread2 = new Thread(() ->{

            printStudent(students.get(4));
            printStudent(students.get(5));

        });
        thread2.start();

    }
    private void printStudent(Student student) {
        System.out.println(student);
    }
}
