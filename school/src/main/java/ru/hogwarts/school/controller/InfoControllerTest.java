package ru.hogwarts.school.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Profile("test")
@RestController
@RequestMapping("info")
public class InfoControllerTest {


    @Value("$server.port")
    private int serverPortTest;

    @GetMapping("/getPort")
    public ResponseEntity getPortTest(){

        int port = serverPortTest;
        return ResponseEntity.ok(port);
    }
}
