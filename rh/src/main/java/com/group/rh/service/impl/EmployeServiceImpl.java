package com.group.rh.service.impl;

import com.group.rh.dto.ReqRes;
import com.group.rh.entity.*;
import com.group.rh.repository.DepartementRepository;
import com.group.rh.repository.EmployeRepository;
import com.group.rh.repository.UsersRepo;
import com.group.rh.service.EmployeService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class EmployeServiceImpl implements EmployeService {
  @Autowired
  private PasswordEncoder passwordEncoder;
  private final EmployeRepository employeRepository;

  @Autowired
  private DepartementRepository departementRepository;

  @Autowired
  private  UsersRepo userRepository;

  @Autowired
  public EmployeServiceImpl(EmployeRepository employeRepository) {
    this.employeRepository = employeRepository;
  }



  public Employe createEmploye(Employe employe) {
    if (employe.getDepartement() != null) {
      Departement departement = employe.getDepartement();
      if ("Ressources Humaines".equalsIgnoreCase(departement.getNom())) {
        // Create an instance of EmployeRH
        EmployeRH employeRH = new EmployeRH();
        employeRH.setNom(employe.getNom());
        employeRH.setPrenom(employe.getPrenom());
        employeRH.setEmail(employe.getEmail());
        employeRH.setNomDutilisateur(employe.getNomDutilisateur());
        employeRH.setMotDePasse(employe.getMotDePasse());
        employeRH.setAdresse(employe.getAdresse());
        employeRH.setNumDeTelephone(employe.getNumDeTelephone());
        employeRH.setSalaire(employe.getSalaire());
        employeRH.setDepartement(employe.getDepartement());
        employeRH.setPoste(employe.getPoste());
        employeRH.setAvSocial(employe.getAvSocial());
        employeRH.setDateEmbauche(employe.getDateEmbauche());
        employeRH.setConges(employe.getConges());
        employeRH.setStatus(employe.getStatus());
        employeRH = employeRepository.save(employeRH);

        // Create user account for EmployeRH
        createUserAccount(employeRH);
        return employeRH;
      }
    }
    // Save regular Employe and create user account
    Employe savedEmploye = employeRepository.save(employe);
    createUserAccount(savedEmploye);
    return savedEmploye;
  }

  private void createUserAccount(Employe employe) {
    OurUsers user = new OurUsers();
    user.setEmploye(employe);
    user.setPassword(passwordEncoder.encode(employe.getMotDePasse()));
    // Check if the employee is an instance of EmployeRH and set the role accordingly
    if (employe instanceof EmployeRH) {
      user.setRole(OurUsers.Role.EMPLOYERH);
    } else {
      user.setRole(OurUsers.Role.EMPLOYE);
    }
    user.setEmail(employe.getEmail());
    user.setName(employe.getNom() + "." + employe.getPrenom());
    this.userRepository.save(user);
    employe.setUser(user);
    employeRepository.save(employe);
  }
  @Override
  public List<Employe> getAllEmployes() {
    return employeRepository.findAll();
  }
  @Override
  public Employe getOneEmploye(int id) {
    return employeRepository.findById(id).orElse(null);
  }

  @Override
  public void updateEmploye(Employe employe) {
    employeRepository.save(employe);
  }

  @Override
  public void deleteEmploye(int id) {
    employeRepository.deleteById(id);
  }


}
