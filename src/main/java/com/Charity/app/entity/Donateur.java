package com.Charity.app.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "donateur")
public class Donateur {

    @Id
    @Column(name = "username", nullable = false)
    private String username; // identique à 'users.username' (JDBC auth)
    @Column(name = "full_name", nullable = false)
    private String fullName;
    @Column(name = "adresse", nullable = false)
    private String adresse;
    @Column(name = "contact", nullable = false)
    private String contact;

    @OneToMany(mappedBy = "donateur", cascade = CascadeType.ALL)
    private List<Don> dons;
}

