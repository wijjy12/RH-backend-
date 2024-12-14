
package com.group.rh.repository;

import com.group.rh.entity.OffreEmploi;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Transactional
public interface OffreEmploiRepository extends JpaRepository<OffreEmploi, Integer> {

    // Enregistre ou met à jour une offre d'emploi
    OffreEmploi save(OffreEmploi offresdemploi);

    // Supprime une offre d'emploi
    void delete(OffreEmploi offresdemploi);

    // Récupère toutes les offres d'emploi
    List<OffreEmploi> findAll();

    //  trouver des offres d'emploi par titre
    List<OffreEmploi> findByTitre(String titre);

}
