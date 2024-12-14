package com.group.rh.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@AllArgsConstructor
@NoArgsConstructor
public class Employe {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employe_seq")
    @SequenceGenerator(name = "employe_seq", sequenceName = "employe_sequence", allocationSize = 1)
    private int id;
    @Column(name = "nom", nullable = false)
    private String nom;
    @Column(name = "prenom", nullable = false)
    private String prenom;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "nom_d_utilisateur", unique = true, nullable = false, length = 100)
    private String nomDutilisateur;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Transient // ça permet de ne pas perister le mot dans employee
    private String motDePasse; /// ON VA PAS PERSISTE  ICI DANS LA BASE DONNEE
    @Column(name = "adresse")
    private String adresse;
    @Column(name = "numéro_de_téléphone", nullable = false)
    private String numDeTelephone;
    @Column(name = "salaire", scale = 2, precision = 8)
    private BigDecimal salaire;
    @OneToOne
    @JoinColumn(name = "user_id")
    private OurUsers user;
    @ManyToOne
    @JoinColumn(name = "departement_id", referencedColumnName = "id")
    private Departement departement;

    @ManyToOne
    @JoinColumn(name = "poste_id", referencedColumnName = "id")
    private Poste poste;

    @ManyToMany
    private Set<Av_Social> avSocial;

    @Column(name = "date_d_embauche")
    @Temporal(TemporalType.DATE)
    private Date dateEmbauche;

    @JsonBackReference(value = "conge-employe")
    @OneToMany(mappedBy = "employe", cascade = CascadeType.ALL)
    private List<Conge> conges = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private EmployeeStatus status;

    @Override
    public String toString() {
        return "Employe{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", adresse='" + adresse + '\'' +
                ", numDeTelephone='" + numDeTelephone + '\'' +
                ", salaire=" + salaire +
                ", dateEmbauche=" + dateEmbauche +
                ", departement=" + (departement != null ? departement.getNom() : "null") +
                ", poste=" + (poste != null ? poste.getTitre() : "null") +
                '}';
    }

}
