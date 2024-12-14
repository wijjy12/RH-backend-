package com.group.rh.controller;

import com.group.rh.entity.Conge;
import com.group.rh.service.CongeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/conges")
public class CongeController {

    private final CongeService congeService;

    @Autowired
    public CongeController(CongeService congeService) {
        this.congeService = congeService;
    }

    @PostMapping("/demande")
    public ResponseEntity<String> demanderConge(@RequestBody Conge conge) {
        congeService.demanderConge(conge);
        return new ResponseEntity<>("Demande de congé créée avec succès.", HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Conge>> getAllDemandesConge() {
        List<Conge> demandesConge = congeService.getAllDemandesConge();
        return new ResponseEntity<>(demandesConge, HttpStatus.OK);
    }

    @GetMapping("/employe/{employeId}")
    public ResponseEntity<List<Conge>> getDemandesCongeByEmploye(@PathVariable("employeId") int employeId) {
        List<Conge> demandesConge = congeService.getDemandesCongeByEmploye(employeId);
        return new ResponseEntity<>(demandesConge, HttpStatus.OK);
    }

    @GetMapping("/{employeId}/approuves")
    public ResponseEntity<List<Conge>> getCongesApprouvesPourPeriode(
            @PathVariable("employeId") int employeId,
            @RequestParam("fromDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
            @RequestParam("toDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate
    ) {
        List<Conge> congesApprouves = congeService.getCongesApprouvesPourPeriode(employeId, fromDate, toDate);
        return new ResponseEntity<>(congesApprouves, HttpStatus.OK);
    }

    @GetMapping("/{employeId}/non-approuves")
    public ResponseEntity<List<Conge>> getCongesNonApprouvesPourPeriode(
            @PathVariable("employeId") int employeId,
            @RequestParam("fromDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
            @RequestParam("toDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate
    ) {
        List<Conge> congesNonApprouves = congeService.getCongesNonApprouvesPourPeriode(employeId, fromDate, toDate);
        return new ResponseEntity<>(congesNonApprouves, HttpStatus.OK);
    }

    @GetMapping("/non-approuves/{employeId}")
    public ResponseEntity<List<Conge>> getCongesNonApprouvesPourEmploye(@PathVariable("employeId") int employeId) {
        List<Conge> congesNonApprouves = congeService.findByEmployeId_IdAndApprouveIsFalse(employeId);
        return new ResponseEntity<>(congesNonApprouves, HttpStatus.OK);
    }

    @PutMapping("/{id}/approve")
    public ResponseEntity<String> approveConge(@PathVariable int id) {
        congeService.approveConge(id);
        return new ResponseEntity<>("Congé approuvé avec succès", HttpStatus.OK);
    }

    @PutMapping("/{id}/reject")
    public ResponseEntity<String> rejectConge(@PathVariable int id) {
        congeService.rejectConge(id);
        return new ResponseEntity<>("Congé rejeté avec succès", HttpStatus.OK);
    }
}