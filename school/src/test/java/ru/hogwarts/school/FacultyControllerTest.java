package ru.hogwarts.school;

import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.controller.AvatarController;
import ru.hogwarts.school.controller.FacultyController;
import ru.hogwarts.school.controller.StudentController;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.service.FacultyService;
import ru.hogwarts.school.serviceImpl.FacultyServiceImpl;

import java.util.Collection;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class FacultyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AvatarController avatarController;

    @MockBean
    private StudentController studentController;

    @MockBean
    private FacultyRepository facultyRepository;

    @SpyBean
    private FacultyServiceImpl facultyService;

    @InjectMocks
    private FacultyController facultyController;

    @Test
    public void createFacultyTest() throws Exception {
        Long id = 1L;
        String name = "Bob";
        String color = "kjhbhgk";

        JSONObject facultyObject = new JSONObject();
        facultyObject.put("name", name);
        facultyObject.put("color", color);


        Faculty faculty = new Faculty(id, name, color);

        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);
        when(facultyRepository.findById(any(Long.class))).thenReturn(Optional.of(faculty));

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/faculty")
                        .content(facultyObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) //receive
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.color").value(color));
    }

    @Test
    public void getFacultyTest() throws Exception {

        Long id = 1L;
        String name = "Bob";
        String color = "kjhbhgk";

        Faculty faculty = new Faculty(id, name, color);

        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);
        when(facultyRepository.getById(any(Long.class))).thenReturn(faculty);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.color").value(color));
    }

    @Test
    public void editFaculty() throws Exception {

        Long id = 1L;
        String name = "Bob";
        String color = "kjhbhgk";

        JSONObject facultyObject = new JSONObject();
        facultyObject.put("name", name);
        facultyObject.put("color", color);

        Faculty editFaculty = new Faculty(id, name, color);

        when(facultyRepository.save(any(Faculty.class))).thenReturn(editFaculty);


        mockMvc.perform(MockMvcRequestBuilders
                        .put("/faculty")
                .content(facultyObject.toString())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.color").value(color));
    }

    @Test
    public void deleteFacultyTest() throws Exception {
        Long id = 1L;

        doNothing().when(facultyRepository).deleteById(id);

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/faculty/" + id))
                .andExpect(status().isOk());
    }

}
