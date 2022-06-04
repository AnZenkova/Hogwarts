package ru.hogwarts.school.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.hogwarts.school.model.Port;
import ru.hogwarts.school.service.PortService;

import java.net.URL;


@Service
@Profile("student")
public class PortServiceImpl implements PortService {


    @Value("${port.url}")
    private String portUrl;

    @Autowired
    private RestTemplate restTemplate;

    public Port getThisPort(){
        URL url = restTemplate.exchange(portUrl, HttpMethod.GET, new HttpEntity<>(HttpHeaders.EMPTY), URL.class).getBody();
        Port port = new Port(url.getPort());
        return port;
    }
}
