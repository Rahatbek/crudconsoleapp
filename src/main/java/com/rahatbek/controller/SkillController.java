package com.rahatbek.controller;

import com.rahatbek.model.Skill;
import com.rahatbek.model.Status;
import com.rahatbek.repository.SkillRepository;
import com.rahatbek.service.SkillService;

import java.util.List;

public class SkillController {

    SkillService skillService;

    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    public List<Skill> getAll() throws Exception {
        return skillService.getAll();
    }

    public Skill getById(Long id) throws Exception {
        return skillService.getById(id);
    }

    public void create(String name, Status status) throws Exception {
        skillService.create(name, status);
    }

    public void update(Long id, String name, Status status) throws Exception {
        skillService.update(id, name, status);
    }

    public void delete(Long id) throws Exception {
        skillService.delete(id);
    }
}
