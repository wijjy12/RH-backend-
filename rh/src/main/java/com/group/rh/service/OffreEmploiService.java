package com.group.rh.service;

import com.group.rh.entity.OffreEmploi;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Transactional
public interface OffreEmploiService {

    void createOffreEmploi(OffreEmploi offreEmploi);
    List<OffreEmploi> getAllOffresEmploi();
    void updateOffreEmploi(OffreEmploi offreEmploi);

    void deleteOffreEmploi(int id);
}
