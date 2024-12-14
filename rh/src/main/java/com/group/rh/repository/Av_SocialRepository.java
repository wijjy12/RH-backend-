package com.group.rh.repository;

import com.group.rh.entity.Av_Social;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface Av_SocialRepository extends JpaRepository<Av_Social, Integer> {
    List<Av_Social> findByNom(String nom); // Recherche les objets AvSocial par leur nom

}

