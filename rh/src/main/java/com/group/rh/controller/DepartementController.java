package com.group.rh.controller;

import com.group.rh.entity.Departement;
import com.group.rh.entity.Poste;
import com.group.rh.service.DepartementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departements")
public class DepartementController {

    private final DepartementService departementService;

    @Autowired
    public DepartementController(DepartementService departementService) {
        this.departementService = departementService;
    }

    @PostMapping
    public ResponseEntity<Void> createDepartement(@RequestBody Departement departement) {
        departementService.createDepartement(departement);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Departement>> getAllDepartements() {
        List<Departement> departements = departementService.getAllDepartements();
        return new ResponseEntity<>(departements, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Departement> getOneDepartement(@PathVariable("id") int id) {
        Departement departement = departementService.getDepartementById(id);
        if (departement != null) {
            return new ResponseEntity<>(departement, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateDepartement(@PathVariable("id") int id, @RequestBody Departement departement) {
        departement.setId(id); // Ensure the ID passed in the request body is correctly set
        departementService.updateDepartement(departement);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartement(@PathVariable("id") int id) {
        departementService.deleteDepartement(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
