package com.group.rh.test;

import com.group.rh.controller.OffreEmploiController;
        import com.group.rh.entity.OffreEmploi;
        import com.group.rh.service.OffreEmploiService;
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

public class OffreEmploiControllerTest {

    @Mock
    private OffreEmploiService offreEmploiService;

    @InjectMocks
    private OffreEmploiController offreEmploiController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateOffreEmploi() {
        OffreEmploi offreEmploi = new OffreEmploi();
        ResponseEntity<Void> response = offreEmploiController.createOffreEmploi(offreEmploi);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(offreEmploiService, times(1)).createOffreEmploi(offreEmploi);
    }

    @Test
    public void testGetAllOffresEmploi() {
        List<OffreEmploi> offresEmploiList = new ArrayList<>();
        offresEmploiList.add(new OffreEmploi());
        offresEmploiList.add(new OffreEmploi());

        when(offreEmploiService.getAllOffresEmploi()).thenReturn(offresEmploiList);

        ResponseEntity<List<OffreEmploi>> response = offreEmploiController.getAllOffresEmploi();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(offresEmploiList, response.getBody());
    }

    @Test
    public void testUpdateOffreEmploi() {
        int id = 1;
        OffreEmploi offreEmploi = new OffreEmploi();
        ResponseEntity<Void> response = offreEmploiController.updateOffreEmploi(id, offreEmploi);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(id, offreEmploi.getId());
        verify(offreEmploiService, times(1)).updateOffreEmploi(offreEmploi);
    }

    @Test
    public void testDeleteOffreEmploi() {
        int id = 1;
        ResponseEntity<Void> response = offreEmploiController.deleteOffreEmploi(id);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(offreEmploiService, times(1)).deleteOffreEmploi(id);
    }
}
