package com.group.rh.service.impl;

import com.group.rh.entity.Admin;
import com.group.rh.repository.AdminRepository;
import com.group.rh.service.AdminService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {
    final private AdminRepository adminRepository;

    // Injection de dépendance via le constructeur
    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository) {

        this.adminRepository = adminRepository;
    }
    @Override
    public Admin getAdmin() {
        return adminRepository.findById(1).orElse(null);
    }

    @Override
    public void updateAdmin(Admin admin) {
        Admin existingAdmin = adminRepository.findById(1).orElse(null);
        if (existingAdmin != null) {
            existingAdmin.setNom(admin.getNom());
            existingAdmin.setPrenom(admin.getPrenom());
            existingAdmin.setEmail(admin.getEmail());
            existingAdmin.setMotDePasse(admin.getMotDePasse());
            existingAdmin.setNumeroTelephone(admin.getNumeroTelephone());
            adminRepository.save(existingAdmin);
        }

    }
}
