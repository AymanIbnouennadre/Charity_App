package com.Charity.app.service;

import com.Charity.app.entity.Organisation;
import com.Charity.app.repository.organisationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrganisationService {

    private final organisationRepository organisationRepository;

    @Autowired
    public OrganisationService(organisationRepository organisationRepository) {
        this.organisationRepository = organisationRepository;
    }

    public List<Organisation> getAllOrganisations() {
        return organisationRepository.findAll();
    }

    public Optional<Organisation> getOrganisationByUsername(String username) {
        return organisationRepository.findById(username);
    }

    public Organisation createOrganisation(Organisation organisation) {
        return organisationRepository.save(organisation);
    }

    public Organisation updateOrganisation(String username, Organisation updatedOrg) {
        return organisationRepository.findById(username)
                .map(org -> {
                    org.setNomOrganisation(updatedOrg.getNomOrganisation());
                    org.setAdresseLegale(updatedOrg.getAdresseLegale());
                    org.setContact(updatedOrg.getContact());
                    org.setTaxId(updatedOrg.getTaxId());
                    org.setApprouvee(updatedOrg.getApprouvee());
                    return organisationRepository.save(org);
                })
                .orElseThrow(() -> new RuntimeException("Organisation introuvable avec username : " + username));
    }

    public void deleteOrganisation(String username) {
        if (!organisationRepository.existsById(username)) {
            throw new RuntimeException("Organisation introuvable avec username : " + username);
        }
        organisationRepository.deleteById(username);
    }
    public Organisation getOrganisationById(String username) {
        return organisationRepository.findById(username)
                .orElseThrow(() -> new RuntimeException("Organisation introuvable : " + username));
    }

}
