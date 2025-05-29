package com.Charity.app.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "organisation")
public class Organisation {

    @Id
    private String username; // identique à 'users.username' dans la table JDBC

    @Column(name = "nom_organisation", nullable = false)
    private String nomOrganisation;

    @Column(name = "adresse_legale", nullable = false)
    private String adresseLegale;

    @Column(name = "contact", nullable = false)
    private String contact;

    @Column(name = "email", nullable = false, unique = true)
    private String taxId;

    @Column(name = "approuvee", nullable = false)
    private Boolean approuvee;

    @OneToMany(mappedBy = "organisation", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Campagne> campagnes;

}
