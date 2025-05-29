package com.Charity.app.service;

import com.Charity.app.entity.Campagne;
import com.Charity.app.entity.Don;
import com.Charity.app.entity.Donateur;
import com.Charity.app.repository.campagneRepository;
import com.Charity.app.repository.donateurRepository;
import com.Charity.app.repository.donRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DonService {

    @Autowired
    private donateurRepository donateurRepository;

    @Autowired
    private donRepository donRepository;

    @Autowired
    private campagneRepository campagneRepository;


    @Autowired
    public DonService(donRepository donRepository) {
        this.donRepository = donRepository;
    }

    // 🔹 Récupérer tous les dons
    public List<Don> getAllDons() {
        return donRepository.findAll();
    }

    // 🔹 Récupérer un don par son ID
    public Optional<Don> getDonById(Long id) {
        return donRepository.findById(id);
    }

    // 🔹 Créer un nouveau don
    public Don createDon(Don don) {
        // 🔍 Récupérer la campagne
        Long campagneId = don.getCampagne().getId();
        Campagne campagne = campagneRepository.findById(campagneId)
                .orElseThrow(() -> new RuntimeException("Campagne introuvable : " + campagneId));

        // 🔍 Récupérer le donateur
        String username = don.getDonateur().getUsername();
        Donateur donateur = donateurRepository.findById(username)
                .orElseThrow(() -> new RuntimeException("Donateur introuvable : " + username));

        // 🎯 Lier les objets persistants
        don.setCampagne(campagne);
        don.setDonateur(donateur);

        return donRepository.save(don);
    }

    // 🔹 Mettre à jour un don existant
    public Don updateDon(Long id, Don updatedDon) {
        return donRepository.findById(id)
                .map(d -> {
                    d.setDonateur(updatedDon.getDonateur());
                    d.setMontant(updatedDon.getMontant());
                    d.setDate(updatedDon.getDate());
                    d.setDescription(updatedDon.getDescription());
                    d.setCampagne(updatedDon.getCampagne());
                    return donRepository.save(d);
                })
                .orElseThrow(() -> new RuntimeException("Don introuvable avec ID : " + id));
    }

    // 🔹 Supprimer un don
    public void deleteDon(Long id) {
        if (!donRepository.existsById(id)) {
            throw new RuntimeException("Don introuvable avec ID : " + id);
        }
        donRepository.deleteById(id);
    }
}
