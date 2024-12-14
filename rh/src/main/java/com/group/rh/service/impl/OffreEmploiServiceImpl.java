package com.group.rh.service.impl;

import com.group.rh.entity.OffreEmploi;
import com.group.rh.repository.OffreEmploiRepository;
import com.group.rh.service.OffreEmploiService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Transactional
public class OffreEmploiServiceImpl implements OffreEmploiService {
    final private OffreEmploiRepository offresEmploiRepository;
    @Autowired
    public OffreEmploiServiceImpl(OffreEmploiRepository offresEmploiRepository) {
        this.offresEmploiRepository = offresEmploiRepository;
    }
    @Override
    public void createOffreEmploi(OffreEmploi offreEmploi) {
        offresEmploiRepository.save(offreEmploi);
    }
    @Override
    public List<OffreEmploi> getAllOffresEmploi() {
        return offresEmploiRepository.findAll();

    }

    @Override
    public void updateOffreEmploi(OffreEmploi offreEmploi) {
        offresEmploiRepository.save(offreEmploi);

    }

    @Override
    public void deleteOffreEmploi(int id) {
        offresEmploiRepository.deleteById(id);

    }
}

