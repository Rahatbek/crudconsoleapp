package com.rahatbek.model;

public class Specialty extends BaseEntity {

    private String name;
    private Status status;

    public Specialty() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
