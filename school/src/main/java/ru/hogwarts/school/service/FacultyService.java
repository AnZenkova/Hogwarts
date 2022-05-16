package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Faculty;

import java.util.List;
import java.util.Set;

public interface FacultyService {

    Faculty createFaculty(Faculty faculty);
    Faculty findFaculty(Long id);
    Faculty editFaculty(Faculty faculty);
    void deleteFaculty(Long id);
    List<Faculty> getAll();
    Set<Faculty> filterFaculty(String color);
}
