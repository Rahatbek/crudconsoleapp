package com.rahatbek.repository;

import com.rahatbek.model.Developer;
import com.rahatbek.model.Status;

import java.util.Set;

public interface DeveloperRepository extends GenericRepository<Developer, Long> {

    void create(String firstName, String lastName, Set<Long> skillsId, Long specialtyId, Status status) throws Exception;
    void create(Long id, String firstName, String lastName);

    void update(Long id, String firstName, String lastName, Set<Long> skillsId, Long specialtyId, Status status) throws Exception;
    void update(Long id, String firstName, String lastName);

}
