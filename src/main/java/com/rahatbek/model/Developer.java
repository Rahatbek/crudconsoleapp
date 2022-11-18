package com.rahatbek.model;

import java.util.List;
import java.util.Set;

public class Developer {

    private Long id;
    private String firstName;
    private String lastName;
    private Set<Long> skillsId;
    private Long specialtyId;
    private Status status;

    public Developer() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Long> getSkillsId() {
        return skillsId;
    }

    public void setSkillsId(Set<Long> skillsId) {
        this.skillsId = skillsId;
    }

    public Long getSpecialtyId() {
        return specialtyId;
    }

    public void setSpecialtyId(Long specialtyId) {
        this.specialtyId = specialtyId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
