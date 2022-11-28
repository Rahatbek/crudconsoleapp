package com.rahatbek.service.impl;

import com.rahatbek.model.Specialty;
import com.rahatbek.model.Status;
import com.rahatbek.repository.DeveloperRepository;
import com.rahatbek.repository.SpecialtyRepository;
import com.rahatbek.service.SpecialtyService;

import java.util.List;

public class SpecialtyServiceImpl implements SpecialtyService {

    private SpecialtyRepository specialtyRepo;
    private DeveloperRepository developerRepo;

    public SpecialtyServiceImpl(SpecialtyRepository specialtyRepo, DeveloperRepository developerRepo) {
        this.specialtyRepo = specialtyRepo;
        this.developerRepo = developerRepo;
    }

    @Override
    public Specialty getById(Long aLong) throws Exception {
        return specialtyRepo.getById(aLong);
    }

    @Override
    public void delete(Long aLong) throws Exception {
        Specialty specialty = getById(aLong);
        specialty.setStatus(Status.DELETED);
        specialtyRepo.update(specialty);
    }

    @Override
    public List<Specialty> getAll() throws Exception {
        return specialtyRepo.getAll();
    }

    @Override
    public void create(String name, Status status) throws Exception {
        Specialty specialty = new Specialty();
        specialty.setId(specialtyRepo.getLastId() + 1);
        specialty.setName(name);
        specialty.setStatus(status);
        specialtyRepo.save(specialty);
    }

    @Override
    public void update(Long id, String name, Status status) throws Exception {
        Specialty byId = getById(id);
        byId.setName(name);
        byId.setStatus(status);
        specialtyRepo.save(byId);
    }
}
