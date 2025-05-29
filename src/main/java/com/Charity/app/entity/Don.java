package com.Charity.app.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Table(name = "don")
public class Don {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double montant;

    @Column(nullable = false)
    private LocalDate date;

    private String description;

    @ManyToOne
    @JoinColumn(name = "campagne_id", nullable = false)
    private Campagne campagne;

    @ManyToOne
    @JoinColumn(name = "donateur_username", nullable = false)
    private Donateur donateur;

    public Don(Double montant, LocalDate date, String description) {
        this.montant = montant;
        this.date = date;
        this.description = description;
    }
}
