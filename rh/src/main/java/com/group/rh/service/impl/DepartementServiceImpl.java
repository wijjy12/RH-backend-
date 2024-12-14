package com.group.rh.service.impl;

import com.group.rh.entity.Departement;
import com.group.rh.entity.Poste;
import com.group.rh.repository.DepartementRepository;
import com.group.rh.service.DepartementService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class DepartementServiceImpl implements DepartementService {

    private final DepartementRepository departementRepository;

    @Autowired
    public DepartementServiceImpl(DepartementRepository departementRepository) {
        this.departementRepository = departementRepository;
    }

    @Override
    public void createDepartement(Departement departement) {
        departementRepository.save(departement);
    }

    @Override
    public List<Departement> getAllDepartements() {
        return departementRepository.findAll();
    }

    @Override
    public void updateDepartement(Departement departement) {
        departementRepository.save(departement);
    }

    @Override
    public void deleteDepartement(int id) {
        departementRepository.deleteById(id);
    }

    @Override
    public Departement getDepartementByNom(String nom) {
        return departementRepository.findByNom(nom);
    }

    @Override
    public Departement getDepartementById(int id) {
        return departementRepository.findById(id).orElse(null);
    }
}
