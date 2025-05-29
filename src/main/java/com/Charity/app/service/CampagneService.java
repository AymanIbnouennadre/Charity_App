package com.Charity.app.service;

import com.Charity.app.entity.Campagne;
import com.Charity.app.repository.campagneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CampagneService {

    private final campagneRepository campagneRepository;

    @Autowired
    public CampagneService(campagneRepository campagneRepository) {
        this.campagneRepository = campagneRepository;
    }

    // 🔹 Récupérer toutes les campagnes
    public List<Campagne> getAllCampagnes() {
        return campagneRepository.findAll();
    }

    // 🔹 Récupérer une campagne par ID
    public Optional<Campagne> getCampagneById(Long id) {
        return campagneRepository.findById(id);
    }

    // 🔹 Créer une nouvelle campagne
    @Autowired
    private OrganisationService organisationService; // Ajoute ceci

    // 🔹 Créer une nouvelle campagne
    public Campagne createCampagne(Campagne campagne) {
        // Récupérer l'organisation persistée (par son username)
        String username = campagne.getOrganisation().getUsername();
        var org = organisationService.getOrganisationById(username); // ⚠️ méthode à créer si elle n'existe pas

        // Associer l'objet Organisation persisté
        campagne.setOrganisation(org);

        return campagneRepository.save(campagne);
    }


    // 🔹 Mettre à jour une campagne existante
    public Campagne updateCampagne(Long id, Campagne updatedCampagne) {
        return campagneRepository.findById(id)
                .map(c -> {
                    c.setName(updatedCampagne.getName());
                    c.setDescription(updatedCampagne.getDescription());
                    c.setStartDate(updatedCampagne.getStartDate());
                    c.setEndDate(updatedCampagne.getEndDate());
                    c.setTargetAmount(updatedCampagne.getTargetAmount());
                    c.setRaisedAmount(updatedCampagne.getRaisedAmount());
                    return campagneRepository.save(c);
                })
                .orElseThrow(() -> new RuntimeException("Campagne introuvable avec ID : " + id));
    }

    // 🔹 Supprimer une campagne
    public void deleteCampagne(Long id) {
        if (!campagneRepository.existsById(id)) {
            throw new RuntimeException("Campagne introuvable avec ID : " + id);
        }
        campagneRepository.deleteById(id);
    }
}
