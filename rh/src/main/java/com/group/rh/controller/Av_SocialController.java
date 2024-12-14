package com.group.rh.controller;

import com.group.rh.entity.Av_Social;
import com.group.rh.service.AvantageSocialService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/avantagesocial")
public class Av_SocialController {
    private final AvantageSocialService avantageSocialService;



        public Av_SocialController(AvantageSocialService avantageSocialService) {
            this.avantageSocialService = avantageSocialService;
        }

    @PostMapping
        public ResponseEntity<String> createAvantageSocial(@RequestBody Av_Social avantageSocial) {
            avantageSocialService.createAvantageSocial(avantageSocial);
            return new ResponseEntity<>("Avantage social créé avec succès", HttpStatus.CREATED);
        }



    @GetMapping
        public ResponseEntity<List<Av_Social>> getAllAvantagesSociaux() {
            List<Av_Social> avantagesSociaux = avantageSocialService.getAllAvantagesSociaux();
            return new ResponseEntity<>(avantagesSociaux, HttpStatus.OK);
        }

        @PutMapping("/{id}")
        public ResponseEntity<String> updateAvantageSocial(@PathVariable int id, @RequestBody Av_Social avantageSocial) {
            avantageSocial.setId(id);
            avantageSocialService.updateAvantageSocial(avantageSocial);
            return new ResponseEntity<>("Avantage social mis à jour avec succès", HttpStatus.OK);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<String> deleteAvantageSocial(@PathVariable int id) {
            avantageSocialService.deleteAvantageSocial(id);
            return new ResponseEntity<>("Avantage social supprimé avec succès", HttpStatus.OK);
        }
    }




