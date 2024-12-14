package com.group.rh.service.impl;

import com.group.rh.entity.Departement;
import com.group.rh.entity.Poste;
import com.group.rh.repository.PosteRepository;
import com.group.rh.service.DepartementService;
import com.group.rh.service.PosteService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class PosteServiceImpl implements PosteService {

    private final PosteRepository posteRepository;
    private final DepartementService departementService;

    @Autowired
    public PosteServiceImpl(PosteRepository posteRepository, DepartementService departementService) {
        this.posteRepository = posteRepository;
        this.departementService = departementService;
    }

    @Override
    public void createPoste(Poste poste) {

        Departement departement = departementService.getDepartementByNom(poste.getDepartement().getNom());
        if (departement == null) {
            departement = new Departement();
            departement.setNom(poste.getDepartement().getNom());
            departementService.createDepartement(departement);
        }
        // Associer le département persistant au poste
        poste.setDepartement(departement);

        // Enregistrer le poste
        posteRepository.save(poste);
    }

    @Override
    public List<Poste> getAllPostes() {
        return posteRepository.findAll();
    }

    @Override
    public void updatePoste(Poste poste) {
        Departement departement = departementService.getDepartementByNom(poste.getDepartement().getNom());
        if (departement == null) {
            departement = new Departement();
            departement.setNom(poste.getDepartement().getNom());
            departementService.createDepartement(departement);
        }
        poste.setDepartement(departement);
        posteRepository.save(poste);
    }

    @Override
    public void deletePoste(Poste poste) {
        posteRepository.deleteById(poste.getId());
    }

    @Override
    public Poste getPosteByTitre(String titre) {
        return posteRepository.findByTitre(titre);
    }

    @Override
    public Poste getPosteById(int id) {
        return posteRepository.findById(id).orElse(null);
    }
}
