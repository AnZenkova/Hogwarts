package ru.hogwarts.school;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.hogwarts.school.controller.StudentController;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private StudentController studentController;

    @Autowired
    private StudentService studentService;

    @Autowired
    private TestRestTemplate testRestTemplate;

    private Long id = 1L;
    private int min = 10;
    private int max = 18;

    @Test
    public void contextLoads() throws Exception{
        Assertions.assertThat(studentController).isNotNull();
    }

    @Test
    public void getStudentTest() throws Exception {
        Assertions
                .assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "/student" + id, String.class))
                .isNotNull();
    }

    @Test
    public void filterStudentTest() throws Exception{
        Assertions
                .assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "/student" + "/age", String.class))
                .isNotNull();
    }

    @Test
    public void findByAgeBetweenTest() throws Exception {
        Assertions
                .assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "/student" + min + ", " + max, String.class))
                .isNotNull();
    }

    @Test
    public void findFacultyTest() throws Exception {
        Assertions
                .assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "/student/faculty", String.class))
                .isNotNull();
    }

    @Test
    public void createStudentTest() throws Exception {

        Student student = new Student(2L, "?????? ??????????", 13);

        Assertions
                .assertThat(this.testRestTemplate.postForObject("http://localhost:" + port + "/student", student, String.class))
                .isNotNull();
    }

    @Test
    public void editStudentTest() throws Exception {
        Student student = new Student();
        HttpEntity<Student> entity = new HttpEntity<>(student);
        Assertions
                .assertThat(this.testRestTemplate.exchange("http://localhost:" + port + "/student", HttpMethod.PUT, entity, Student.class))
                .isNotNull();
    }

    @Test
    public void deleteStudentTest() throws Exception {

        Student student = new Student(2L, "?????? ??????????", 13);

        Student savedStudent = studentService.createStudent(student);
        ResponseEntity<String> resp = testRestTemplate.exchange("http://localhost:" + port + "/student/" + savedStudent.getId(), HttpMethod.DELETE, new HttpEntity<>(""), String.class);
        assertEquals(HttpStatus.OK, resp.getStatusCode());
    }
}
