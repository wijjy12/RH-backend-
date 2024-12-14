package com.group.rh.test;

import com.group.rh.controller.EmployeController;
import com.group.rh.entity.Departement;
import com.group.rh.entity.Employe;
import com.group.rh.entity.Poste;
import com.group.rh.service.DepartementService;
import com.group.rh.service.PosteService;
import com.group.rh.service.EmployeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class EmployeControllerTest {

    @Mock
    private EmployeService employeService;

    @Mock
    private DepartementService departementService;

    @Mock
    private PosteService posteService;

    @InjectMocks
    private EmployeController employeController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateEmploye() {
        Employe employe = new Employe();
        Departement departement = new Departement();
        departement.setNom("IT");
        employe.setDepartement(departement);
        Poste poste = new Poste();
        poste.setTitre("Développeur");
        employe.setPoste(poste);

        when(departementService.getDepartementByNom("IT")).thenReturn(departement);
      //  when(posteService.getPosteByTitle("Développeur")).thenReturn(poste);
        // tu es entrain de faire le test? ICI

        ResponseEntity<String> response = employeController.createEmploye(employe);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(employeService, times(1)).createEmploye(employe);
    }

    @Test
    public void testGetAllEmployes() {
        List<Employe> employes = new ArrayList<>();
        employes.add(new Employe());
        employes.add(new Employe());

        when(employeService.getAllEmployes()).thenReturn(employes);

        ResponseEntity<List<Employe>> response = employeController.getAllEmployes();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(employes, response.getBody());
    }

    @Test
    public void testGetOneEmploye() {
        int id = 1;
        Employe employe = new Employe();
        when(employeService.getOneEmploye(id)).thenReturn(employe);

        ResponseEntity<Employe> response = employeController.getOneEmploye(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(employe, response.getBody());
    }

    @Test
    public void testUpdateEmploye() {
        int id = 1;
        Employe employe = new Employe();
        Departement departement = new Departement();
        departement.setNom("IT");
        employe.setDepartement(departement);
        Poste poste = new Poste();
        poste.setTitre("Développeur");
        employe.setPoste(poste);

        when(departementService.getDepartementByNom("IT")).thenReturn(departement);
        //when(posteService.getPosteByTitle("Développeur")).thenReturn(poste);

        ResponseEntity<Void> response = employeController.updateEmploye(id, employe);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(id, employe.getId());
        verify(employeService, times(1)).updateEmploye(employe);
    }

    @Test
    public void testDeleteEmployee() {
        int id = 1;
        ResponseEntity<Void> response = employeController.deleteEmploye(id);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(employeService, times(1)).deleteEmploye(id);
    }

    @Test
    public void testGetAllEmployeeByProgrammeFormation() {
        int id = 1;
        List<Employe> employes = new ArrayList<>();
        employes.add(new Employe());
        employes.add(new Employe());

        when(employeService.getAllEmployesByProgrammeFormation(id)).thenReturn(employes);

        ResponseEntity<List<Employe>> response = employeController.getAllEmployesByProgrammeFormation(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(employes, response.getBody());
    }
}
