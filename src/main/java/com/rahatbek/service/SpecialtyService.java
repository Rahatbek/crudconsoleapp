package com.rahatbek.service;

import com.rahatbek.model.Specialty;
import com.rahatbek.model.Status;

public interface SpecialtyService extends GenericService<Specialty, Long> {
    void create(String name, Status status) throws Exception;

    void update(Long id, String name, Status status) throws Exception;
}
