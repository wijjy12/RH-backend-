package com.group.rh.test;

import com.group.rh.controller.AdminController;
import com.group.rh.entity.Admin;
import com.group.rh.service.AdminService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AdminControllerTest {

    @Mock
    private AdminService adminService;

    @InjectMocks
    private AdminController adminController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAdmin() {
        // Créer un objet Admin fictif pour simuler la réponse du service
        Admin admin = new Admin();
        admin.setId(1);
        admin.setNom("John");
        admin.setPrenom("Doe");
        admin.setEmail("john.doe@example.com");
        admin.setMotDePasse("password");
        admin.setNumeroTelephone("123456789");

        // Définir le comportement simulé du service
        when(adminService.getAdmin()).thenReturn(admin);

        // Appeler la méthode du contrôleur à tester
        ResponseEntity<Admin> response = adminController.getAdmin();

        // Vérifier si la réponse est correcte
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(admin, response.getBody());
    }

    @Test
    public void testUpdateAdmin() {
        // Créer un objet Admin fictif pour simuler la requête
        Admin admin = new Admin();
        admin.setId(1);
        admin.setNom("Jane");
        admin.setPrenom("Doe");
        admin.setEmail("jane.doe@example.com");
        admin.setMotDePasse("newPassword");
        admin.setNumeroTelephone("987654321");

        // Appeler la méthode du contrôleur à tester
        ResponseEntity<Void> response = adminController.updateAdmin(admin);

        // Vérifier si la méthode du service est appelée avec le bon argument
        verify(adminService, times(1)).updateAdmin(admin);

        // Vérifier si la réponse est correcte
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}

