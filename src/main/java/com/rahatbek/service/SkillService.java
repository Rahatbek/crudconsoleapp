package com.rahatbek.service;

import com.rahatbek.model.Skill;
import com.rahatbek.model.Status;

public interface SkillService extends GenericService<Skill, Long>{
    void create(String name, Status status) throws Exception;
    void update(Long id, String name, Status status) throws Exception;
}
