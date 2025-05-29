package com.Charity.app.controller;

import com.Charity.app.entity.Donateur;
import com.Charity.app.service.DonateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/donateurs")
public class DonateurController {

    private final DonateurService donateurService;

    @Autowired
    public DonateurController(DonateurService donateurService) {
        this.donateurService = donateurService;
    }

    @GetMapping
    public List<Donateur> getAll() {
        return donateurService.getAllDonateurs();
    }

    @GetMapping("/{username}")
    public Donateur getOne(@PathVariable String username) {
        return donateurService.getDonateurByUsername(username)
                .orElseThrow(() -> new RuntimeException("Donateur introuvable"));
    }

    @PostMapping
    public Donateur create(@RequestBody Donateur donateur) {
        return donateurService.createDonateur(donateur);
    }

    @PutMapping("/{username}")
    public Donateur update(@PathVariable String username, @RequestBody Donateur updatedDonateur) {
        return donateurService.updateDonateur(username, updatedDonateur);
    }

    @DeleteMapping("/{username}")
    public void delete(@PathVariable String username) {
        donateurService.deleteDonateur(username);
    }
}
