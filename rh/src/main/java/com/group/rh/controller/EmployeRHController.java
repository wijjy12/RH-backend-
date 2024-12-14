
package com.group.rh.controller;

import com.group.rh.entity.EmployeRH;
import com.group.rh.service.EmployeRHService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employes-rh")
public class EmployeRHController{

    private final EmployeRHService employeRHService;

    @Autowired
    public EmployeRHController(EmployeRHService employeRHService) {
        this.employeRHService = employeRHService;
    }

    @PostMapping
    public ResponseEntity<Void> createEmployeRH(@RequestBody EmployeRH employeRH) {
        employeRHService.createEmployeRH(employeRH);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<EmployeRH>> getAllEmployeRH() {
        List<EmployeRH> employeRHList = employeRHService.getAllEmployeRH();
        return new ResponseEntity<>(employeRHList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeRH> getEmployeRHById(@PathVariable("id") int id) {
        EmployeRH employeRH = employeRHService.getEmployeRHById(id);
        if (employeRH != null) {
            return new ResponseEntity<>(employeRH, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateEmployeRH(@PathVariable("id") int id, @RequestBody EmployeRH employeRH) {
        employeRH.setId(id);
        employeRHService.updateEmployeRH(employeRH);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployeRH(@PathVariable("id") int id) {
        employeRHService.deleteEmployeRH(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
