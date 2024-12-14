package com.group.rh.service.impl;

import com.group.rh.entity.Candidat;
import com.group.rh.entity.OurUsers;
import com.group.rh.repository.CandidatRepository;
import com.group.rh.repository.UsersRepo;
import com.group.rh.service.CandidatService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Transactional
public class CandidatServiceImpl implements CandidatService {

    final private CandidatRepository candidatRepository;
    @Autowired
    private UsersRepo userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public CandidatServiceImpl(CandidatRepository candidatRepository) {

        this.candidatRepository = candidatRepository;
    }
    @Override
    public void createCandidat(Candidat candidat) {
        candidat = candidatRepository.save(candidat);
        createUserAccount(candidat);
    }
    private void createUserAccount(Candidat candidat) {
        OurUsers user = new OurUsers();
        user.setCandidat(candidat);
        user.setPassword(passwordEncoder.encode(candidat.getMotDePasse()));
        user.setRole(OurUsers.Role.CANDIDAT);
        user.setEmail(candidat.getEmail());
        user.setName(candidat.getNom() + "." + candidat.getPrenom());
        this.userRepository.save(user);
        candidat.setUser(user);
        candidatRepository.save(candidat);
    }

    @Override
    public List<Candidat> getAllCandidats() {
        return candidatRepository.findAll();
    }

    @Override
    public Candidat getCandidatById(int id) {
        return candidatRepository.findById(id).orElse(null);
    }

    @Override
    public void updateCandidat(Long id,Candidat candidat) {
        candidat.setId(Math.toIntExact(id));
        candidatRepository.save(candidat);
    }

    @Override
    public void deleteCandidat(int id) {
        candidatRepository.deleteById(id);
    }
}



