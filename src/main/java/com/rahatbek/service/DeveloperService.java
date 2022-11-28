package com.rahatbek.service;

import com.rahatbek.model.Developer;
import com.rahatbek.model.Status;

import java.util.Set;

public interface DeveloperService extends GenericService<Developer, Long> {

    void create(String firstName, String lastName, Long specialtyId, Set<Long> skillsId, Status status) throws Exception;

    void update(Long id, String firstName, String lastName, Long specialtyId, Set<Long> skillsId, Status status) throws Exception;

    void checkEdit(Long id) throws Exception;

    void checkSkill(Long id) throws Exception;

    void checkSpecialty(Long id) throws Exception;
}
