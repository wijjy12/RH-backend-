package com.group.rh.test;

import com.group.rh.entity.Employe;
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

public class ProgformationControllerTest {

    @Mock
    private ProgrammeFormationService programmeFormationService;

    @InjectMocks
    private ProgformationController progformationController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateProgrammeFormation() {
        Progformation programmeFormation = new Progformation();
        ResponseEntity<Void> response = progformationController.createProgrammeFormation(programmeFormation);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(programmeFormationService, times(1)).createProgrammeFormation(programmeFormation);
    }

    @Test
    public void testGetAllProgrammesFormation() {
        List<Progformation> programmesFormation = new ArrayList<>();
        programmesFormation.add(new Progformation());
        programmesFormation.add(new Progformation());

        when(programmeFormationService.getAllProgrammesFormation()).thenReturn(programmesFormation);

        ResponseEntity<List<Progformation>> response = progformationController.getAllProgrammesFormation();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(programmesFormation, response.getBody());
    }

    @Test
    public void testGetProgrammeFormationById() {
        int id = 1;
        Progformation programmeFormation = new Progformation();
        programmeFormation.setId(id);

        when(programmeFormationService.getProgrammeFormationById(id)).thenReturn(programmeFormation);

        ResponseEntity<Progformation> response = progformationController.getProgrammeFormationById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(programmeFormation, response.getBody());
    }

    @Test
    public void testUpdateProgrammeFormation() {
        int id = 1;
        Progformation programmeFormation = new Progformation();
        programmeFormation.setId(id);
        ResponseEntity<Void> response = progformationController.updateProgrammeFormation(id, programmeFormation);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(programmeFormationService, times(1)).updateProgrammeFormation(programmeFormation);
    }

    @Test
    public void testDeleteProgrammeFormation() {
        int id = 1;
        ResponseEntity<Void> response = progformationController.deleteProgrammeFormation(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(programmeFormationService, times(1)).deleteProgrammeFormation(id);
    }

    @Test
    public void testIntegrateEmployee() {
        int programmeFormationId = 1;
        Employe employe = new Employe();
        ResponseEntity<Void> response = progformationController.integrateEmployee(programmeFormationId, employe);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(programmeFormationService, times(1)).integrateEmployee(programmeFormationId, employe);
    }
}
