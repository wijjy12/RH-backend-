package com.group.rh.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.Collection;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Departement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "nom", nullable = false)
    private String nom;
    @Column(name= "description")
    private String description;
    @Override
    public String toString() {
        return "Departement{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                '}';
    }

    @OneToMany(mappedBy = "departement", cascade = CascadeType.ALL)
    @JsonBackReference(value = "departement-employe")
    private List<Employe> employes = new ArrayList<>();

    @JsonBackReference(value = "departement-poste")
    @OneToMany(mappedBy = "departement", cascade = CascadeType.ALL)
    private Collection<Poste> postes = new ArrayList<>();
}


