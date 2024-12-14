package com.group.rh.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data // Génère les getters, setters, toString, equals, et hashCode
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Admin  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "nom", nullable = false)
    private String nom;
    @Column(name = "prenom", nullable = false)
    private String prenom;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "mot_de_passe", nullable = false)
    private String motDePasse;
    @Column(name = "numero_de_tel", nullable = false)
    private String numeroTelephone;
    @JsonBackReference  // Empêche la sérialisation JSON récursive
    @OneToOne
    @JoinColumn(name = "user_id")
    private OurUsers user;
}








