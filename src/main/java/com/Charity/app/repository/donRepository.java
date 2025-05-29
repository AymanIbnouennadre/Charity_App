package com.Charity.app.repository;

import com.Charity.app.entity.Don;
import org.springframework.data.jpa.repository.JpaRepository;

public interface donRepository extends JpaRepository<Don, Long> {
    // Custom query methods can be added here if needed
    // For example, to find donations by a specific donor name:
    // List<don> findByDonateurName(String donateurName);

    // Or to find donations for a specific campaign:
    // List<don> findByCampagneId(Long campagneId);
}
