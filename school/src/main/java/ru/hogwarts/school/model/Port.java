package ru.hogwarts.school.model;

public class Port {

    private Integer port;

    public Port(Integer port) {
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "Port{" +
                "port=" + port +
                '}';
    }
}
