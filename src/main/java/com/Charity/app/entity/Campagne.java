package com.Charity.app.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "campagne")
public class Campagne {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(name = "target_amount", nullable = false)
    private Double targetAmount;

    @Column(name = "raised_amount")
    private Double raisedAmount;

    @ManyToOne
    @JoinColumn(name = "organisation_username")
    @JsonBackReference
    private Organisation organisation;


    @OneToMany(mappedBy = "campagne", cascade = CascadeType.ALL)
    private List<Don> donations;

    public Campagne(String name, String description, LocalDate startDate, LocalDate endDate) {
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
