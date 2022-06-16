package ru.hogwarts.school.controller;


import org.apache.logging.log4j.util.PerformanceSensitive;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("faculty")
public class FacultyController {

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    // Добавляем факультет в базу данных
    @PostMapping
    public Faculty createFaculty(@RequestBody Faculty faculty) {
        return facultyService.createFaculty(faculty);
    }

    //Получаем факультет по id
    @GetMapping("{id}")
    public ResponseEntity getFaculty(@PathVariable Long id) {
        Faculty faculty = facultyService.findFaculty(id);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    // Вносим изменения в факультет
    @PutMapping
    public ResponseEntity editFaculty(@RequestBody Faculty faculty) {
        Faculty foundFaculty = facultyService.editFaculty(faculty);
        return ResponseEntity.ok(foundFaculty);
    }

    // Удаляем фкультет по id
    @DeleteMapping("{id}")
    public ResponseEntity deleteFaculty(@PathVariable Long id) {
        facultyService.deleteFaculty(id);
        return ResponseEntity.ok().build();
    }

    // Получаем факультет по цвету
    @GetMapping({"color"})
    public ResponseEntity filterFaculty(@PathVariable String color) {
        if (color == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(facultyService.filterFaculty(color));
    }

    // Получаем факультет по цвету и имени игнорируя регистр
    @GetMapping("/colorAndName")
    public ResponseEntity findFaculty(@RequestParam String color,
                                     @RequestParam String name) {
        if (color != null && !color.isBlank()) {
            return ResponseEntity.ok(facultyService.findByColorIgnoreCase(color));
        }
        if (name != null && !name.isBlank()) {
            return ResponseEntity.ok(facultyService.findByNameIgnoreCase(name));
        }
        return ResponseEntity.ok().build();
    }

    // Получаем список студентов по факультету
    @GetMapping("{faculty}")
    public Collection<Student> getStudents(@PathVariable String faculty) {
        return facultyService.getStudents(faculty);
    }

    // Получаем факультет по цвету и названию
    @GetMapping("/get{color}{name}")
    public ResponseEntity<List<Faculty>> getFacultyByColorAndName(@PathVariable String color,
                                                                     @PathVariable String name) {
        List<Faculty> faculties = facultyService.getFacultyByColorAndName(color, name);
        return ResponseEntity.ok(faculties);
    }

    // Получаем факультет у которого самое длиное название
    @GetMapping("/findNameMaxLength")
    public ResponseEntity findNameMaxLength() {
        String facultyName = facultyService.findNameMaxLength();
        return ResponseEntity.ok(facultyName);
    }

    @GetMapping("/int")
    public int getInt() {

        int sum = Stream.iterate(1, a -> a +1)
                .parallel()
                .limit(1_000_000)
                .reduce(0, (a, b) -> a + b );
        return sum;
    }
}
