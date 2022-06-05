package ru.hogwarts.school.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URL;


@Service
@Profile("student")
public class PortServiceImpl {

    @Value("${port.url}")
    private String portUrl;

    @Autowired
    private RestTemplate restTemplate;

    public int getThisPort(){
        URL url = restTemplate.exchange(portUrl, HttpMethod.GET, new HttpEntity<>(HttpHeaders.EMPTY), URL.class).getBody();
        int port = url.getPort();
        return port;
    }
}
