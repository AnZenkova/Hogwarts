package ru.hogwarts.school.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Profile("student")
@RestController
@RequestMapping("info")
public class InfoController {

    @Value("$server.port")
    private int serverPort;

    @GetMapping("/getPort")
    public ResponseEntity getPort(){

        int port = serverPort;
        return ResponseEntity.ok(port);
    }


}
