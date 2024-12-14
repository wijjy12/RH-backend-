package com.group.rh.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "employe_rh") // Specify the table name for EmployeRH
public class EmployeRH extends Employe {
    // You can add specific fields for EmployeRH if needed
    @OneToOne
    @JoinColumn(name = "user_id")
    private OurUsers user;
}

