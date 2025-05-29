package com.Charity.app.service;

import com.Charity.app.entity.Donateur;
import com.Charity.app.repository.donateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DonateurService {

    private final donateurRepository donateurRepository;

    @Autowired
    public DonateurService(donateurRepository donateurRepository) {
        this.donateurRepository = donateurRepository;
    }

    // 🔹 Retourner tous les donateurs
    public List<Donateur> getAllDonateurs() {
        return donateurRepository.findAll();
    }

    // 🔹 Trouver un donateur par son username (email)
    public Optional<Donateur> getDonateurByUsername(String username) {
        return donateurRepository.findById(username);
    }

    // 🔹 Ajouter un nouveau donateur
    public Donateur createDonateur(Donateur donateur) {
        return donateurRepository.save(donateur);
    }

    // 🔹 Mettre à jour un donateur existant
    public Donateur updateDonateur(String username, Donateur updatedDonateur) {
        return donateurRepository.findById(username)
                .map(d -> {
                    d.setFullName(updatedDonateur.getFullName());
                    d.setAdresse(updatedDonateur.getAdresse());
                    d.setContact(updatedDonateur.getContact());
                    return donateurRepository.save(d);
                })
                .orElseThrow(() -> new RuntimeException("Donateur introuvable avec username : " + username));
    }

    // 🔹 Supprimer un donateur
    public void deleteDonateur(String username) {
        if (!donateurRepository.existsById(username)) {
            throw new RuntimeException("Donateur introuvable avec username : " + username);
        }
        donateurRepository.deleteById(username);
    }
}
