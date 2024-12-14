package com.group.rh.service;

import com.group.rh.entity.Admin;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public interface AdminService {

        Admin getAdmin();

        void updateAdmin(Admin admin);
    }


