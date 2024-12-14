package com.group.rh.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Set;
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Av_Social {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "nom", nullable = false)
    private String nom;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "montant", nullable = true, precision = 10, scale = 2)
    private BigDecimal montant;
    @ManyToMany
    private Set<Employe> employe;

}
