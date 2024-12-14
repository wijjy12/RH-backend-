package com.group.rh.controller;

import com.group.rh.entity.OffreEmploi;
import com.group.rh.service.OffreEmploiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/offres-emploi")
public class OffreEmploiController {

    private final OffreEmploiService offreEmploiService;

    @Autowired
    public OffreEmploiController(OffreEmploiService offreEmploiService) {
        this.offreEmploiService = offreEmploiService;
    }

    @PostMapping
    public ResponseEntity<Void> createOffreEmploi(@RequestBody OffreEmploi offreEmploi) {
        offreEmploiService.createOffreEmploi(offreEmploi);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<OffreEmploi>> getAllOffresEmploi() {
        List<OffreEmploi> offresEmploi = offreEmploiService.getAllOffresEmploi();
        return new ResponseEntity<>(offresEmploi, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateOffreEmploi(@PathVariable("id") int id, @RequestBody OffreEmploi offreEmploi) {
        offreEmploi.setId(id); // Assure que l'id passé dans le corps de la requête soit correctement défini
        offreEmploiService.updateOffreEmploi(offreEmploi);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOffreEmploi(@PathVariable("id") int id) {
        offreEmploiService.deleteOffreEmploi(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
