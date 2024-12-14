package com.group.rh.service;

import com.group.rh.entity.EmployeRH;

import java.util.List;
public interface EmployeRHService {
    void createEmployeRH(EmployeRH employeRH);
    List<EmployeRH> getAllEmployeRH();
    EmployeRH getEmployeRHById(int id);
    void updateEmployeRH(EmployeRH employeRH);
    void deleteEmployeRH(int id);
}
