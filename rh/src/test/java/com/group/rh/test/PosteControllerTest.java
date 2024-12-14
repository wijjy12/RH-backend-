package com.group.rh.test;

import com.group.rh.controller.PosteController;
        import com.group.rh.entity.Poste;
        import com.group.rh.service.PosteService;
        import org.junit.jupiter.api.BeforeEach;
        import org.junit.jupiter.api.Test;
        import org.mockito.InjectMocks;
        import org.mockito.Mock;
        import org.mockito.MockitoAnnotations;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.ResponseEntity;

        import java.math.BigDecimal;
        import java.util.ArrayList;
        import java.util.List;

        import static org.junit.jupiter.api.Assertions.assertEquals;
        import static org.mockito.Mockito.*;

public class PosteControllerTest {

    @Mock
    private PosteService posteService;

    @InjectMocks
    private PosteController posteController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreatePoste() {
        Poste poste = new Poste();
        ResponseEntity<Void> response = posteController.createPoste(poste);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(posteService, times(1)).createPoste(poste);
    }

    @Test
    public void testGetAllPostes() {
        List<Poste> postes = new ArrayList<>();
        postes.add(new Poste());
        postes.add(new Poste());

        when(posteService.getAllPostes()).thenReturn(postes);

        ResponseEntity<List<Poste>> response = posteController.getAllPostes();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(postes, response.getBody());
    }

    @Test
    public void testUpdatePoste() {
        int id = 1;
        Poste poste = new Poste();
        poste.setId(id);
        ResponseEntity<Void> response = posteController.updatePoste(id, poste);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(posteService, times(1)).updatePoste(poste);
    }

    @Test
    public void testDeletePoste() {
        int id = 1;
        Poste poste = new Poste();
        poste.setId(id);
        ResponseEntity<Void> response = posteController.deletePoste(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(posteService, times(1)).deletePoste(poste);
    }
}
