package com.group.rh.service;

import com.group.rh.dto.ReqRes;
import com.group.rh.entity.Employe;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public interface EmployeService {

    Employe createEmploye(Employe employe);

    List<Employe> getAllEmployes();
    Employe getOneEmploye(int id);
    void updateEmploye(Employe employe);
    void deleteEmploye(int id);

}

