package com.Charity.app.controller;

import com.Charity.app.entity.Don;
import com.Charity.app.service.DonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dons")
public class DonController {

    private final DonService donService;

    @Autowired
    public DonController(DonService donService) {
        this.donService = donService;
    }

    // 🔹 GET /api/dons
    @GetMapping
    public List<Don> getAllDons() {
        return donService.getAllDons();
    }

    // 🔹 GET /api/dons/{id}
    @GetMapping("/{id}")
    public Don getDonById(@PathVariable Long id) {
        return donService.getDonById(id)
                .orElseThrow(() -> new RuntimeException("Don introuvable avec l’ID : " + id));
    }

    // 🔹 POST /api/dons
    @PostMapping
    public Don createDon(@RequestBody Don don) {
        return donService.createDon(don);
    }

    // 🔹 PUT /api/dons/{id}
    @PutMapping("/{id}")
    public Don updateDon(@PathVariable Long id, @RequestBody Don updatedDon) {
        return donService.updateDon(id, updatedDon);
    }

    // 🔹 DELETE /api/dons/{id}
    @DeleteMapping("/{id}")
    public void deleteDon(@PathVariable Long id) {
        donService.deleteDon(id);
    }
}
