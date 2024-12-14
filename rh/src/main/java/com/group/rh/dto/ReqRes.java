package com.group.rh.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.group.rh.entity.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)  // Inclut uniquement les champs non nuls dans la sérialisation JSON
@JsonIgnoreProperties(ignoreUnknown = true)  // Ignore les propriétés inconnues lors de la désérialisation JSON
@Getter
@Setter
public class ReqRes {

    private int statusCode;
    private String error;
    private String message;
    private String token;
    private String refreshToken;
    private String expirationTime;
    private String name;
    private String role;
    private String email;
    private String password;
    private OurUsers ourUsers;
    private List<OurUsers> ourUsersList;
    private String adresse;
    private Employe employe;
    private Candidat candidat;
    private EmployeRH employeRH;
    private Admin admin;
    private String nom;
    private String prenom;
    private String numeroTelephone;
    private BigDecimal salaire;
    private Departement departement;
    private Poste poste;
    private Date dateEmbauche;

}
