package com.rahatbek.controller;

import com.rahatbek.model.Specialty;
import com.rahatbek.model.Status;
import com.rahatbek.repository.SpecialtyRepository;

import java.util.List;

public class SpecialtyController {

    SpecialtyRepository specialtyRepository;

    public SpecialtyController(SpecialtyRepository specialtyRepository) {
        this.specialtyRepository = specialtyRepository;
    }

    public List<Specialty> getAll() {
        return specialtyRepository.getAll();
    }

    public Specialty getById(Long id) {
        return specialtyRepository.getById(id);
    }

    public void deleteById(Long id) {
        specialtyRepository.deleteById(id);
    }

    public Specialty update(long id, String name, Status status) {
        Specialty updatedSpecialty = specialtyRepository.getById(id);
        updatedSpecialty.setName(name);
        updatedSpecialty.setStatus(status);
        return specialtyRepository.update(updatedSpecialty);
    }

    public Specialty save(String name) {
        Specialty newSpecialty = new Specialty();
        newSpecialty.setName(name);
        newSpecialty.setStatus(Status.ACTIVE);
        return specialtyRepository.save(newSpecialty);
    }
}
