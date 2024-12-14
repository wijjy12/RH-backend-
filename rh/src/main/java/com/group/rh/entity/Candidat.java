package com.group.rh.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Candidat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "nom", nullable = false)
    private String nom;
    @Column(name = "prenom", nullable = false)
    private String prenom;
    @Column(name = "adresse", nullable = false)
    private String adresse;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "numero_de_telephone")
    private String numeroTelephone;
    @Column(name = "mot_de_passe", nullable = false)
    private String motDePasse;
    @Column(name= "diplôme")
    private String diplome;
    @OneToOne
    @JoinColumn(name = "user_id")
    private OurUsers user;
    @Column(name= "expérience")
    private double experience;
    @Column(name = "domaine_experience")
    private String domaineExperience;
    @Lob  // Indique un champ de type Large Object
    @Column(name = "cv", columnDefinition = "LONGBLOB") // Déclare une colonne pour stocker de grandes quantités de données binaires
    private byte[] cv;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateCreation;


}


















