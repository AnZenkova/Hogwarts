package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Collection<Student> findByAgeBetween(int min, int max);

    @Query(value = "SELECT COUNT(*) FROM student", nativeQuery = true)
    Integer getNumberOfStudents();

    @Query(value = "SELECT  AVG(age) AS age FROM student", nativeQuery = true)
    Integer getMiddleAgeOfStudents();

    @Query(value = "SELECT * FROM student OFFSET 5", nativeQuery = true)
    List<Student> getLastFiveStudent();

    List<Student> getStudentByName(String name);

    List<Student> findAll();
}
