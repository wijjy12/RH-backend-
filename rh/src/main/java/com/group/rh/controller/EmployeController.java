package com.group.rh.controller;

import com.group.rh.entity.Departement;
import com.group.rh.entity.Employe;
import com.group.rh.entity.Poste;
import com.group.rh.service.DepartementService;
import com.group.rh.service.EmployeService;
import com.group.rh.service.PosteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/employes")
public class EmployeController {

    private final EmployeService employeService;
    private final DepartementService departementService;
    private final PosteService posteService;

    public EmployeController(EmployeService employeService, DepartementService departementService, PosteService posteService) {
        this.employeService = employeService;
        this.departementService = departementService;
        this.posteService = posteService;
    }

    // C4EST BIEN cette methode qui sera appelée ?
    @PostMapping
    public ResponseEntity<String> createEmploye(@RequestBody Employe employe) {
        try {
            Departement departement = departementService.getDepartementByNom(employe.getDepartement().getNom());
            employe.setDepartement(departement);
            Poste poste = posteService.getPosteByTitre(employe.getPoste().getTitre());
            employe.setPoste(poste);
            employeService.createEmploye(employe);
            System.out.println(employe.getNom());
            return new ResponseEntity<>("Employe ajouté avec succès.", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error creating employee: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping

    public ResponseEntity<List<Employe>> getAllEmployes() {
        List<Employe> employes = employeService.getAllEmployes();
        return new ResponseEntity<>(employes, HttpStatus.OK);
    }

    @GetMapping("/{id}")

    public ResponseEntity<Employe> getOneEmploye(@PathVariable("id") int id) {
        Employe employe = employeService.getOneEmploye(id);
        if (employe != null) {
            return new ResponseEntity<>(employe, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")

    public ResponseEntity<Void> updateEmploye(@PathVariable("id") int id, @RequestBody Employe employe) {
        try {
            Departement departement = departementService.getDepartementByNom(employe.getDepartement().getNom());
            employe.setDepartement(departement);
            Poste poste = posteService.getPosteByTitre(employe.getPoste().getTitre());
            employe.setPoste(poste);
            employe.setId(id); // Ensure the id is correctly set
            employeService.updateEmploye(employe);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")

    public ResponseEntity<Void> deleteEmploye(@PathVariable("id") int id) {
        try {
            employeService.deleteEmploye(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}








