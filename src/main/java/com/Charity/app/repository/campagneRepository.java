package com.Charity.app.repository;

import com.Charity.app.entity.Campagne;
import org.springframework.data.jpa.repository.JpaRepository;

public interface campagneRepository extends JpaRepository<Campagne, Long> {
    // Custom query methods can be added here if needed
    // For example, to find campaigns by a specific name:
    // List<campagne> findByName(String name);

    // Or to find campaigns that are active:
    // List<campagne> findByActive(boolean active);
}
