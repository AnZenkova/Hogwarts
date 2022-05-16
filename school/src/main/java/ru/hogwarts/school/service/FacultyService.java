package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface FacultyService {

    Faculty createFaculty(Faculty faculty);
    Faculty findFaculty(Long id);
    Faculty editFaculty(Faculty faculty);
    void deleteFaculty(Long id);
    List<Faculty> getAll();
    Set<Faculty> filterFaculty(String color);
    List<Faculty> findByColorIgnoreCase(String color);
    List<Faculty> findByNameIgnoreCase(String name);
    Collection<Student> getStudents(Faculty faculty);
}
