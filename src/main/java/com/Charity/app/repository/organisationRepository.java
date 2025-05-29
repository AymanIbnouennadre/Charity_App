package com.Charity.app.repository;

import com.Charity.app.entity.Organisation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface organisationRepository extends JpaRepository<Organisation, String> {
    // Custom query methods can be added here if needed
    // For example, to find organisations by name:
    // List<organisation> findByName(String name);

    // Or to find organisations by a specific attribute:
    // List<organisation> findByAttribute(String attribute);
}
