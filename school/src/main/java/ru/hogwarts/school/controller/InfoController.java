package ru.hogwarts.school.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hogwarts.school.model.Port;
import ru.hogwarts.school.service.PortService;

import java.net.MalformedURLException;

@RestController
@RequestMapping("info")
public class InfoController {

    private PortService portService;

    public InfoController(PortService portService) {
        this.portService = portService;
    }

    @GetMapping("/getPort")
    public ResponseEntity getPort(){

        Port port = portService.getThisPort();
        return ResponseEntity.ok(port);
    }

}
