package com.group.rh.controller;
import com.group.rh.entity.Candidat;
import com.group.rh.service.CandidatService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/candidats")
public class CandidatController {

    private final CandidatService candidatService;

    public CandidatController(final CandidatService candidatService) {
        this.candidatService = candidatService;
    }

    @PostMapping
    public ResponseEntity<String> addCandidat(
            @RequestParam("nom") String nom,
            @RequestParam("prenom") String prenom,
            @RequestParam("adresse") String adresse,
            @RequestParam("email") String email,
            @RequestParam("numeroTelephone") String numeroTelephone,
            @RequestParam("motDePasse") String motDePasse,
            @RequestParam("diplome") String diplome,
            @RequestParam("experience") double experience,
            @RequestParam("domaineExperience") String domaineExperience,
            @RequestParam("cv") MultipartFile cv,
            @RequestParam("dateCreation") String dateCreationStr) throws IOException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dateCreation = null;
        try {
            dateCreation = sdf.parse(dateCreationStr);
        } catch (ParseException e) {
            return new ResponseEntity<>("Invalid date format", HttpStatus.BAD_REQUEST);
        }

        Candidat candidat = new Candidat();
        candidat.setNom(nom);
        candidat.setPrenom(prenom);
        candidat.setAdresse(adresse);
        candidat.setEmail(email);
        candidat.setNumeroTelephone(numeroTelephone);
        candidat.setMotDePasse(motDePasse);
        candidat.setDiplome(diplome);
        candidat.setExperience(experience);
        candidat.setDomaineExperience(domaineExperience);
        candidat.setCv(cv.getBytes());
        candidat.setDateCreation(dateCreation);

        candidatService.createCandidat(candidat);
        return new ResponseEntity<>("Candidat ajouté avec succès.", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Candidat>> getAllCandidats() {
        List<Candidat> candidats = candidatService.getAllCandidats();
        return new ResponseEntity<>(candidats, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Candidat> getCandidatById(@PathVariable("id") int id) {
        Candidat candidat = candidatService.getCandidatById(id);
        if (candidat != null) {
            return new ResponseEntity<>(candidat, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/cv/{id}")
    public ResponseEntity<byte[]> getCv(@PathVariable int id) {
        Candidat candidat = candidatService.getCandidatById(id);
        if (candidat == null) {
            return ResponseEntity.notFound().build();
        }
        byte[] cv = candidat.getCv();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "cv_" + candidat.getNom() + ".pdf");
        return new ResponseEntity<>(cv, headers, HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<String> updateCandidat(@PathVariable Long id, @RequestBody Candidat candidat) {
        candidatService.updateCandidat(id, candidat);
        return new ResponseEntity<>("Candidat mis à jour avec succès.", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCandidat(@PathVariable("id") int id) {
        candidatService.deleteCandidat(id);
        return new ResponseEntity<>("Candidat supprimé avec succès.", HttpStatus.OK);
    }
}
