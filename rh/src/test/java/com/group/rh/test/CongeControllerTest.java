package com.group.rh.test;
import com.group.rh.controller.CongeController;
        import com.group.rh.entity.Conge;
        import com.group.rh.entity.LeaveStatus;
        import com.group.rh.service.CongeService;
        import org.junit.jupiter.api.BeforeEach;
        import org.junit.jupiter.api.Test;
        import org.mockito.InjectMocks;
        import org.mockito.Mock;
        import org.mockito.MockitoAnnotations;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.ResponseEntity;

        import java.time.LocalDate;
        import java.util.ArrayList;
        import java.util.List;

        import static org.junit.jupiter.api.Assertions.assertEquals;
        import static org.mockito.Mockito.*;

public class CongeControllerTest {

    @Mock
    private CongeService congeService;

    @InjectMocks
    private CongeController congeController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testDemanderConge() {
        Conge conge = new Conge();
        conge.setRaison("Vacances");
        ResponseEntity<String> response = congeController.demanderConge(conge);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(congeService, times(1)).demanderConge(conge);
    }

    @Test
    public void testGetAllDemandesConge() {
        List<Conge> demandesConge = new ArrayList<>();
        demandesConge.add(new Conge());
        demandesConge.add(new Conge());

        when(congeService.getAllDemandesConge()).thenReturn(demandesConge);

        ResponseEntity<List<Conge>> response = congeController.getAllDemandesConge();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(demandesConge, response.getBody());
    }

    @Test
    public void testGetDemandesCongeByEmploye() {
        int employeId = 1;
        List<Conge> demandesConge = new ArrayList<>();
        demandesConge.add(new Conge());
        demandesConge.add(new Conge());

        when(congeService.getDemandesCongeByEmploye(employeId)).thenReturn(demandesConge);

        ResponseEntity<List<Conge>> response = congeController.getDemandesCongeByEmploye(employeId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(demandesConge, response.getBody());
    }

    @Test
    public void testGetCongesApprouvesPourPeriode() {
        int employeId = 1;
        LocalDate fromDate = LocalDate.of(2024, 5, 1);
        LocalDate toDate = LocalDate.of(2024, 5, 31);
        List<Conge> congesApprouves = new ArrayList<>();
        congesApprouves.add(new Conge());
        congesApprouves.add(new Conge());

       // when(congeService.getCongesApprouvesPourPeriode(employeId, LeaveStatus.APPROVED, fromDate, toDate)).thenReturn(congesApprouves);

        ResponseEntity<List<Conge>> response = congeController.getCongesApprouvesPourPeriode(employeId, fromDate, toDate);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(congesApprouves, response.getBody());
    }

    @Test
    public void testGetCongesNonApprouvesPourEmploye() {
        int employeId = 1;
        List<Conge> congesNonApprouves = new ArrayList<>();
        congesNonApprouves.add(new Conge());
        congesNonApprouves.add(new Conge());

        when(congeService.findByEmployeId_IdAndApprouveIsFalse(employeId)).thenReturn(congesNonApprouves);

        ResponseEntity<List<Conge>> response = congeController.getCongesNonApprouvesPourEmploye(employeId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(congesNonApprouves, response.getBody());
    }

    @Test
    public void testGetCongesNonApprouves() {
        int employeId = 1;
        LocalDate fromDate = LocalDate.of(2024, 5, 1);
        LocalDate toDate = LocalDate.of(2024, 5, 31);
        List<Conge> congesNonApprouves = new ArrayList<>();
        congesNonApprouves.add(new Conge());
        congesNonApprouves.add(new Conge());

        // APRES TU VAS REFAIRE
       // when(congeService.getCongesNonApprouves(employeId, LeaveStatus.DENIED, fromDate, toDate)).thenReturn(congesNonApprouves);

       // ResponseEntity<List<Conge>> response = congeController.getCongesNonApprouves(employeId, fromDate, toDate);

       // assertEquals(HttpStatus.OK, response.getStatusCode());
      //  assertEquals(congesNonApprouves, response.getBody());
    }
}
