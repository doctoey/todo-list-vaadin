package org.vaadin.example.dashboard;

import java.util.List;

public class ServiceHealth {

    private Status status;
    private String city;
    private int input;
    private int output;

    public enum Status {
        EXCELLENT, OK, FAILING;
    }

    public ServiceHealth(Status status, String city, int input, int output) {
        this.status = status;
        this.city = city;
        this.input = input;
        this.output = output;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getInput() {
        return input;
    }

    public void setInput(int input) {
        this.input = input;
    }

    public int getOutput() {
        return output;
    }

    public void setOutput(int output) {
        this.output = output;
    }

    public String getStatusAsString() {
        return status != null ? status.name() : "UNKNOWN";
    }

    public static List<ServiceHealth> sampleData() {
        return List.of(
                new ServiceHealth(Status.EXCELLENT, "Berlin", 324, 1540),
                new ServiceHealth(Status.OK, "London", 311, 1320),
                new ServiceHealth(Status.FAILING, "New York", 300, 1219)
        );
    }
}
