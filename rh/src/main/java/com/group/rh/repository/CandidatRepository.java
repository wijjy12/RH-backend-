package com.group.rh.repository;


import com.group.rh.entity.Candidat;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface CandidatRepository extends JpaRepository<Candidat, Integer> {

    List<Candidat> findByNom(String nom);

    List<Candidat> findByPrenom(String prenom);

    List<Candidat> findByDomaineExperience(String domaineExperience);
}

