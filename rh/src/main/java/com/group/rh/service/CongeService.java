package com.group.rh.service;

import com.group.rh.entity.Conge;

import java.time.LocalDate;
import java.util.List;

public interface CongeService {
    void demanderConge(Conge conge);

    List<Conge> getAllDemandesConge();
    void approveConge(int id);

    void rejectConge(int id);

    List<Conge> getDemandesCongeByEmploye(int employeId);

    List<Conge> getCongesApprouvesPourPeriode(int employeId, LocalDate fromDate, LocalDate toDate);

    List<Conge> getCongesNonApprouvesPourPeriode(int employeId, LocalDate fromDate, LocalDate toDate);

    List<Conge> findByEmployeId_IdAndApprouveIsFalse(int employeId);
}
