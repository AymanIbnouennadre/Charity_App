package com.Charity.app.controller;

import com.Charity.app.entity.Campagne;
import com.Charity.app.service.CampagneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/campagnes")
public class CampagneController {

    private final CampagneService campagneService;

    @Autowired
    public CampagneController(CampagneService campagneService) {
        this.campagneService = campagneService;
    }

    @GetMapping
    public List<Campagne> getAll() {
        return campagneService.getAllCampagnes();
    }

    @GetMapping("/{id}")
    public Campagne getById(@PathVariable Long id) {
        return campagneService.getCampagneById(id)
                .orElseThrow(() -> new RuntimeException("Campagne introuvable"));
    }

    @PostMapping
    public Campagne create(@RequestBody Campagne campagne) {
        return campagneService.createCampagne(campagne);
    }

    @PutMapping("/{id}")
    public Campagne update(@PathVariable Long id, @RequestBody Campagne updatedCampagne) {
        return campagneService.updateCampagne(id, updatedCampagne);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        campagneService.deleteCampagne(id);
    }
}
