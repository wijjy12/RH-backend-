package com.group.rh.controller;

import com.group.rh.entity.Poste;
import com.group.rh.service.PosteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/postes")
public class PosteController {

    private final PosteService posteService;

    @Autowired
    public PosteController(PosteService posteService) {
        this.posteService = posteService;
    }

    @PostMapping
    public ResponseEntity<Void> createPoste(@RequestBody Poste poste) {
        posteService.createPoste(poste);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Poste>> getAllPostes() {
        List<Poste> postes = posteService.getAllPostes();
        return new ResponseEntity<>(postes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Poste> getOnePoste(@PathVariable("id") int id) {
        Poste poste = posteService.getPosteById(id);
        if (poste != null) {
            return new ResponseEntity<>(poste, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePoste(@PathVariable("id") int id, @RequestBody Poste poste) {
        poste.setId(id); // Assure que l'id passé dans le corps de la requête soit correctement défini
        posteService.updatePoste(poste);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePoste(@PathVariable("id") int id) {
        Poste poste = new Poste();
        poste.setId(id);
        posteService.deletePoste(poste);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
