package com.group.rh.service;

import com.group.rh.entity.Poste;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public interface PosteService {
    void createPoste(Poste poste);
    List<Poste> getAllPostes();
    void updatePoste(Poste poste);
    void deletePoste(Poste poste);
    Poste getPosteByTitre(String titre);
    Poste getPosteById(int id);
}
