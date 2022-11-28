package com.rahatbek.controller;

import com.rahatbek.model.Specialty;
import com.rahatbek.model.Status;
import com.rahatbek.service.SpecialtyService;

import java.util.List;

public class SpecialtyController {

    SpecialtyService specialtyService;

    public SpecialtyController(SpecialtyService specialtyService) {
        this.specialtyService = specialtyService;
    }

    public List<Specialty> getAll() throws Exception {
        return specialtyService.getAll();
    }

    public Specialty getById(Long id) throws Exception {
        return specialtyService.getById(id);
    }

    public void create(String name, Status status) throws Exception {
        specialtyService.create(name, status);
    }

    public void update(Long id, String name, Status status) throws Exception {
        specialtyService.update(id, name, status);
    }

    public void delete(Long id) throws Exception {
        specialtyService.delete(id);
    }
}
