package com.group.rh.service.impl;

import com.group.rh.entity.Conge;
import com.group.rh.entity.Employe;
import com.group.rh.entity.LeaveStatus;
import com.group.rh.repository.CongeRepository;
import com.group.rh.repository.EmployeRepository;
import com.group.rh.service.CongeService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class CongeServiceImpl implements CongeService {

    private final CongeRepository congeRepository;
    private final EmployeRepository employeRepository; // Add this line
    @Autowired
    public CongeServiceImpl(CongeRepository congeRepository,EmployeRepository employeRepository) {
        this.congeRepository = congeRepository;
        this.employeRepository = employeRepository; // Add this line

    }

    @Override
    public void demanderConge(Conge conge) {
        Employe employe = employeRepository.findById(conge.getEmploye().getId())
                .orElseThrow(() -> new RuntimeException("Employe non trouvé"));
        conge.setEmploye(employe); // Ensure the employe is managed by Hibernate
        congeRepository.save(conge);
    }

    @Override
    public List<Conge> getAllDemandesConge() {
        return congeRepository.findAll();
    }
    @Override
    public void approveConge(int id) {
        Conge conge = congeRepository.findById(id).orElseThrow(() -> new RuntimeException("Congé non trouvé"));
        conge.setApprouve(true);
        conge.setStatus(LeaveStatus.APPROVED);
        congeRepository.save(conge);
    }

    @Override
    public void rejectConge(int id) {
        Conge conge = congeRepository.findById(id).orElseThrow(() -> new RuntimeException("Congé non trouvé"));
        conge.setApprouve(false);
        conge.setStatus(LeaveStatus.DENIED);
        congeRepository.save(conge);
    }


    @Override
    public List<Conge> getDemandesCongeByEmploye(int employeId) {
        return congeRepository.findByEmployeId_Id(employeId);
    }

    @Override
    public List<Conge> getCongesApprouvesPourPeriode(int employeId, LocalDate fromDate, LocalDate toDate) {
        return congeRepository.findByEmployeId_IdAndStatusAndFromDateBetween(employeId, LeaveStatus.APPROVED, fromDate, toDate);
    }

    @Override
    public List<Conge> getCongesNonApprouvesPourPeriode(int employeId, LocalDate fromDate, LocalDate toDate) {
        return congeRepository.findByEmployeId_IdAndStatusAndFromDateBetween(employeId, LeaveStatus.DENIED, fromDate, toDate);
    }

    @Override
    public List<Conge> findByEmployeId_IdAndApprouveIsFalse(int employeId) {
        return congeRepository.findByEmployeId_IdAndApprouveIsFalse(employeId);
    }
}
