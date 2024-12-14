package com.group.rh.controller;

import com.group.rh.entity.Admin;
import com.group.rh.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping
    public ResponseEntity<Admin> getAdmin() {
        Admin admin = adminService.getAdmin();
        if (admin != null) {
            return new ResponseEntity<>(admin, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping
    public ResponseEntity<Void> updateAdmin(@RequestBody Admin admin) {
        adminService.updateAdmin(admin);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}



