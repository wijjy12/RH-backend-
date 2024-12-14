package com.group.rh.service;

import com.group.rh.entity.Departement;
import com.group.rh.entity.Poste;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.group.rh.repository.DepartementRepository;

import java.util.List;

@Service
@Transactional
public interface DepartementService {
    void createDepartement(Departement departement);
    List<Departement> getAllDepartements();
    void updateDepartement(Departement departement);
    void deleteDepartement(int id);
    Departement getDepartementByNom(String nom);

    Departement getDepartementById(int id);


}
