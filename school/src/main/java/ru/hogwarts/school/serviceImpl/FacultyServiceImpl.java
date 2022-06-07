package ru.hogwarts.school.serviceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FacultyServiceImpl implements FacultyService {

    private final FacultyRepository facultyRepository;

    Logger logger = LoggerFactory.getLogger(Faculty.class);

    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }


    public Faculty createFaculty(Faculty faculty) {
        logger.debug("The method is called createFaculty from the faculty class");
        return facultyRepository.save(faculty);
    }

    public Faculty findFaculty(Long id) {
        logger.debug("The method is called findFaculty from the faculty class");
        return facultyRepository.getById(id);
    }

    public Faculty editFaculty(Faculty faculty) {
        logger.debug("The method is called editFaculty from the faculty class");
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(Long id) {
        logger.debug("The method is called deleteFaculty from the faculty class");
        facultyRepository.deleteById(id);
    }

    public List<Faculty> getAll() {
        logger.debug("The method is called createFaculty from the faculty class");
        return facultyRepository.findAll();
    }

    public Set<Faculty> filterFaculty(String color) {
        logger.debug("The method is called getAll from the faculty class");
        List<Faculty> faculties = getAll();
        Set<Faculty> filter = faculties.stream()
                .filter(s -> s.getColor().equals(color))
                .collect(Collectors.toSet());
        return filter;
    }

    public List<Faculty> findByColorIgnoreCase(String color) {
        logger.debug("The method is called findByColorIgnoreCase from the faculty class");
        return facultyRepository.findByColorIgnoreCase(color);
    }
    public List<Faculty> findByNameIgnoreCase(String name) {
        logger.debug("The method is called findByNameIgnoreCase from the faculty class");
        return facultyRepository.findByNameIgnoreCase(name);
    }

    public Collection<Student> getStudents(String faculty) {
        logger.debug("The method is called getStudents from the faculty class");
        List<Faculty> faculties = getAll();
        Faculty faculty1 = faculties.stream()
                .filter(s -> s.getName().equals(faculty))
                .findFirst()
                .get();
        return faculty1.getStudents();
    }

    public List<Faculty> getFacultyByColorAndName(String color, String name) {
        logger.debug("The method is called getFacultyByColorAndName from the faculty class");
        return facultyRepository.getFacultyByColorAndName(color, name);
    }

    public String findNameMaxLength() {
        List<Faculty> faculties = facultyRepository.findAll();
        return faculties.stream()
                .map(s -> s.getName())
                .max(Comparator.comparing(String::length))
                .get();
    }
}
