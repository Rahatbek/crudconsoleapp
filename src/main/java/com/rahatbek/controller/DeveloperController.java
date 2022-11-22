package com.rahatbek.controller;

import com.rahatbek.model.Developer;
import com.rahatbek.repository.DeveloperRepository;

import java.util.List;

public class DeveloperController {

    DeveloperRepository repository;

    public DeveloperController(DeveloperRepository repository) {
        this.repository = repository;
    }

    public List<Developer> getAll() throws Exception {
        return repository.getAll();
    }

    public Developer getById(Long id) throws Exception {
        return repository.getById(id);
    }

    public void create(Long id, String firstName, String lastName) {
        repository.create(id, firstName, lastName);
    }

    public void update(Long id, String firstName, String lastName) {
        repository.update(id, firstName, lastName);
    }


}
