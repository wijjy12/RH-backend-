package com.group.rh.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Poste {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "titre", nullable = false)
    private String titre;
    @Column(name= "description")
    private String description;
    @Column(name= "salaire", precision = 10, scale = 2)
    private BigDecimal salaire;
    @OneToMany(mappedBy = "poste")
    @JsonBackReference(value = "poste-employe")
    private List<Employe> employes = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "departement_id")
    private Departement departement;
    @Override
    public String toString() {
        return "Poste{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", departement=" + (departement != null ? departement.getNom() : "null") +
                '}';
    }
    // Constructor to accept ID
    public Poste(int id) {
        this.id = id;
    }

}

