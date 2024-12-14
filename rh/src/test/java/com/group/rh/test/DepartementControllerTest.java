package com.group.rh.test;

import com.group.rh.controller.DepartementController;
        import com.group.rh.entity.Departement;
        import com.group.rh.service.DepartementService;
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

public class DepartementControllerTest {

    @Mock
    private DepartementService departementService;

    @InjectMocks
    private DepartementController departementController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateDepartement() {
        Departement departement = new Departement();
        ResponseEntity<Void> response = departementController.createDepartement(departement);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(departementService, times(1)).createDepartement(departement);
    }

    @Test
    public void testGetAllDepartements() {
        List<Departement> departements = new ArrayList<>();
        departements.add(new Departement());
        departements.add(new Departement());

        when(departementService.getAllDepartements()).thenReturn(departements);

        ResponseEntity<List<Departement>> response = departementController.getAllDepartements();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(departements, response.getBody());
    }

    @Test
    public void testUpdateDepartement() {
        int id = 1;
        Departement departement = new Departement();
        ResponseEntity<Void> response = departementController.updateDepartement(id, departement);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(id, departement.getId());
        verify(departementService, times(1)).updateDepartement(departement);
    }

    @Test
    public void testDeleteDepartement() {
        int id = 1;
        ResponseEntity<Void> response = departementController.deleteDepartement(id);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(departementService, times(1)).deleteDepartement(id);
    }
}
