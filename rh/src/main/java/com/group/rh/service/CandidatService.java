package com.group.rh.service;

import com.group.rh.entity.Candidat;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Transactional
public interface CandidatService {

        void createCandidat(Candidat candidat);
        List<Candidat> getAllCandidats();
        Candidat getCandidatById(int id);
        void updateCandidat(Long id,Candidat candidat);
        void deleteCandidat(int id);
    }


