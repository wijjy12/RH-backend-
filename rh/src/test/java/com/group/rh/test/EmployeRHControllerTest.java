package com.group.rh.test;

import com.group.rh.controller.EmployeRHController;
        import com.group.rh.entity.EmployeRH;
        import com.group.rh.service.EmployeRHService;
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

public class EmployeRHControllerTest {

    @Mock
    private EmployeRHService employeRHService;

    @InjectMocks
    private EmployeRHController employeRHController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateEmployeRH() {
        EmployeRH employeRH = new EmployeRH();
        ResponseEntity<Void> response = employeRHController.createEmployeRH(employeRH);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(employeRHService, times(1)).createEmployeRH(employeRH);
    }

    @Test
    public void testGetAllEmployeRH() {
        List<EmployeRH> employeRHList = new ArrayList<>();
        employeRHList.add(new EmployeRH());
        employeRHList.add(new EmployeRH());

        when(employeRHService.getAllEmployeRH()).thenReturn(employeRHList);

        ResponseEntity<List<EmployeRH>> response = employeRHController.getAllEmployeRH();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(employeRHList, response.getBody());
    }

    @Test
    public void testGetEmployeRHById() {
        int id = 1;
        EmployeRH employeRH = new EmployeRH();
        when(employeRHService.getEmployeRHById(id)).thenReturn(employeRH);

        ResponseEntity<EmployeRH> response = employeRHController.getEmployeRHById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(employeRH, response.getBody());
    }

    @Test
    public void testUpdateEmployeRH() {
        int id = 1;
        EmployeRH employeRH = new EmployeRH();
        ResponseEntity<Void> response = employeRHController.updateEmployeRH(id, employeRH);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(id, employeRH.getId());
        verify(employeRHService, times(1)).updateEmployeRH(employeRH);
    }

    @Test
    public void testDeleteEmployeRH() {
        int id = 1;
        ResponseEntity<Void> response = employeRHController.deleteEmployeRH(id);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(employeRHService, times(1)).deleteEmployeRH(id);
    }
}
