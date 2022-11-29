package com.rahatbek.model;

import java.util.List;
import java.util.Set;

public class Developer extends BaseEntity{
    private String firstName;
    private String lastName;
    private Long specialtyId;
    private Set<Long> skillsId;

    private Specialty specialty;
    private Set<Skill> skills;
    private Status status;

    public Developer() {
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

    public Specialty getSpecialty() {
        return specialty;
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }

    public Set<Skill> getSkills() {
        return skills;
    }

    public void setSkills(Set<Skill> skills) {
        this.skills = skills;
    }

    @Override
    public String toString() {
        return "Developer{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", specialtyId=" + specialtyId +
                ", skillsId=" + skillsId +
                ", specialty=" + specialty +
                ", skills=" + skills +
                ", status=" + status +
                '}';
    }
}
