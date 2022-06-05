package ru.hogwarts.school.serviceImpl;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("!student")
public class PortServiceTest {

    public int getThisPort() {

        int port = 3030;
        return port;
    }
}
