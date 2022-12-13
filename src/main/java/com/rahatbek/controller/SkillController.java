package com.rahatbek.controller;

import com.rahatbek.model.Skill;
import com.rahatbek.model.Status;
import com.rahatbek.repository.SkillRepository;

import java.util.List;

public class SkillController {

    SkillRepository skillRepository;

    public SkillController(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    public List<Skill> getAll() {
        return skillRepository.getAll();
    }

    public Skill getById(Long id) {
        return skillRepository.getById(id);
    }

    public void deleteById(Long id) {
        skillRepository.deleteById(id);
    }

    public Skill update(long id, String name, Status status) {
        Skill updatedSkill = skillRepository.getById(id);
        updatedSkill.setName(name);
        updatedSkill.setStatus(status);
        return skillRepository.update(updatedSkill);
    }

    public Skill save(String name) {
        Skill newSkill = new Skill();
        newSkill.setName(name);
        newSkill.setStatus(Status.ACTIVE);
        return skillRepository.save(newSkill);
    }
}
