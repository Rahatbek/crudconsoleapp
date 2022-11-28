package com.rahatbek.service.impl;

import com.rahatbek.model.Skill;
import com.rahatbek.model.Status;
import com.rahatbek.repository.DeveloperRepository;
import com.rahatbek.repository.SkillRepository;
import com.rahatbek.service.SkillService;

import java.util.List;

public class SkillServiceImpl implements SkillService {

    private SkillRepository skillRepo;
    private DeveloperRepository developerRepo;

    public SkillServiceImpl(SkillRepository skillRepo, DeveloperRepository developerRepo) {
        this.skillRepo = skillRepo;
        this.developerRepo = developerRepo;
    }

    @Override
    public Skill getById(Long id) throws Exception {
        return skillRepo.getById(id);
    }

    @Override
    public void delete(Long aLong) throws Exception {
        Skill skill = getById(aLong);
        skill.setStatus(Status.DELETED);
        skillRepo.save(skill);
    }

    @Override
    public List<Skill> getAll() throws Exception {
        return null;
    }

    @Override
    public void create(String name, Status status) throws Exception {
        Skill skill = new Skill();
        skill.setId(skillRepo.getLastId() + 1);
        skill.setName(name);
        skill.setStatus(status);
        skillRepo.save(skill);
    }

    @Override
    public void update(Long id, String name, Status status) throws Exception {
        Skill skill = getById(id);
        skill.setName(name);
        skill.setStatus(status);
        skillRepo.update(skill);
    }
}
