package com.rahatbek.repository;

import com.rahatbek.model.Developer;
import com.rahatbek.model.Skill;
import com.rahatbek.model.Specialty;

public interface DeveloperRepository extends GenericRepository<Developer, Long> {

    boolean isContainSkill(Skill skill) throws Exception;

    boolean isContainSpecialty(Specialty specialty) throws Exception;

    void checkEdit(Long id) throws Exception;

    void checkSkill(Long id) throws Exception;

    void checkSpecialty(Long id) throws Exception;
}
