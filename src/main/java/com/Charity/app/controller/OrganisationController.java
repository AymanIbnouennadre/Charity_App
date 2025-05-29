package com.Charity.app.controller;

import com.Charity.app.entity.Organisation;
import com.Charity.app.service.OrganisationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/organisations")
public class OrganisationController {

    private final OrganisationService organisationService;

    @Autowired
    public OrganisationController(OrganisationService organisationService) {
        this.organisationService = organisationService;
    }

    @GetMapping
    public List<Organisation> getAll() {
        return organisationService.getAllOrganisations();
    }

    @GetMapping("/{username}")
    public Organisation getOne(@PathVariable String username) {
        return organisationService.getOrganisationByUsername(username)
                .orElseThrow(() -> new RuntimeException("Organisation introuvable"));
    }

    @PostMapping
    public Organisation create(@RequestBody Organisation organisation) {
        return organisationService.createOrganisation(organisation);
    }

    @PutMapping("/{username}")
    public Organisation update(@PathVariable String username, @RequestBody Organisation updatedOrg) {
        return organisationService.updateOrganisation(username, updatedOrg);
    }

    @DeleteMapping("/{username}")
    public void delete(@PathVariable String username) {
        organisationService.deleteOrganisation(username);
    }
}
