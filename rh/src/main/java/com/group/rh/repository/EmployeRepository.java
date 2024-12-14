
package com.group.rh.repository;

import com.group.rh.entity.Departement;
import com.group.rh.entity.Employe;
import com.group.rh.entity.Poste;
import com.group.rh.entity.EmployeeStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface EmployeRepository extends JpaRepository<Employe, Integer> {

    Page<Employe> findAll(Pageable pageable);


    Page<Employe> findByPrenomContainingOrNomContaining(Pageable pageable, String prenom, String nom);
    // Méthode pour trouver les employés par leur département
    List<Employe> findByDepartement(Departement departement);

    // Méthode pour trouver les employés par leur poste
    List<Employe> findByPoste(Poste poste);

    // Méthode pour trouver les employés actifs
    List<Employe> findByStatus(EmployeeStatus status);

}

