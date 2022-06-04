package ru.hogwarts.school.serviceImpl;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Port;
import ru.hogwarts.school.service.PortService;


@Service
@Profile("!student")
public class PortServiceTest implements PortService {
    @Override
    public Port getThisPort() {

        Port port = new Port(3030);
        return port;
    }
}
