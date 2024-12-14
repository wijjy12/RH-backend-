package com.group.rh.repository;

import com.group.rh.entity.Poste;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PosteRepository extends JpaRepository<Poste, Integer> {
    Poste findByTitre(String titre);  // Ajoutez cette méthode
}
