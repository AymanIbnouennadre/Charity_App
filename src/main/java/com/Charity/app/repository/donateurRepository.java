package com.Charity.app.repository;

import com.Charity.app.entity.Donateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface donateurRepository extends JpaRepository<Donateur,String> {
}
