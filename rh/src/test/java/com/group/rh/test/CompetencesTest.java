package com.group.rh.test;



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

public class CompetencesTest {

    @Mock
    private CompetencesService competencesService;

    @InjectMocks
    private CompetencesController competencesController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllCompetences() {
        List<Competences> competencesList = new ArrayList<>();
        competencesList.add(new Competences());
        competencesList.add(new Competences());

        when(competencesService.getAllCompetences()).thenReturn(competencesList);

        ResponseEntity<List<Competences>> response = competencesController.getAllCompetences();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(competencesList, response.getBody());
    }

    @Test
    public void testGetCompetencesById() {
        long id = 1L;
        Competences competences = new Competences();
        competences.setId(id);
        competences.setNom("Test");

        when(competencesService.getCompetencesById(id)).thenReturn(competences);

        ResponseEntity<Competences> response = competencesController.getCompetencesById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(competences, response.getBody());
    }

    @Test
    public void testCreateCompetences() {
        Competences competences = new Competences();
        competences.setNom("Test");

        ResponseEntity<Void> response = competencesController.createCompetences(competences);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(competencesService, times(1)).createCompetences(competences);
    }

    @Test
    public void testUpdateCompetences() {
        long id = 1L;
        Competences competences = new Competences();
        competences.setId(id);
        competences.setNom("Updated Test");

        ResponseEntity<Void> response = competencesController.updateCompetences(id, competences);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(competencesService, times(1)).updateCompetences(id, competences);
    }

    @Test
    public void testDeleteCompetences() {
        long id = 1L;

        ResponseEntity<Void> response = competencesController.deleteCompetences(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(competencesService, times(1)).deleteCompetences(id);
    }
}
