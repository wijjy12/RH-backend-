package com.group.rh.service.impl;

import com.group.rh.entity.Employe;
import com.group.rh.entity.EmployeRH;
import com.group.rh.entity.OurUsers;
import com.group.rh.repository.EmployeRHRepository;
import com.group.rh.repository.UsersRepo;
import com.group.rh.service.EmployeRHService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeRHServiceImpl implements EmployeRHService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UsersRepo userRepository;

    private final EmployeRHRepository employeRHRepository;

    @Autowired
    public EmployeRHServiceImpl(EmployeRHRepository employeRHRepository) {
        this.employeRHRepository = employeRHRepository;
    }

    @Override
    public void createEmployeRH(EmployeRH employeRH) {
        employeRH = employeRHRepository.save(employeRH);
        createUserAccount(employeRH);
    }
    private void createUserAccount(EmployeRH employeRH) {
        System.out.println("===> EMPLOYE RH === "); // je cherche ce scope
        OurUsers user = new OurUsers();
        user.setEmploye(employeRH);
        user.setPassword(passwordEncoder.encode(employeRH.getMotDePasse()));
        user.setRole(OurUsers.Role.EMPLOYE);
        user.setEmail(employeRH.getEmail());
        user.setName(employeRH.getNom() + "." + employeRH.getPrenom()); // concatenation du nom & prenom
        this.userRepository.save(user);
    }

    @Override
    public List<EmployeRH> getAllEmployeRH() {
        return employeRHRepository.findAll();
    }

    @Override
    public EmployeRH getEmployeRHById(int id) {
        return employeRHRepository.findById(id).orElse(null);
    }

    @Override
    public void updateEmployeRH(EmployeRH employeRH) {
        employeRHRepository.save(employeRH);
    }

    @Override
    public void deleteEmployeRH(int id) {
        employeRHRepository.deleteById(id);
    }
}
