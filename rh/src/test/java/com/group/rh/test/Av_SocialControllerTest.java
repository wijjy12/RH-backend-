package com.group.rh.test;
import com.group.rh.controller.Av_SocialController;
import com.group.rh.entity.Av_Social;
import com.group.rh.service.AvantageSocialService;
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

public class Av_SocialControllerTest {

    @Mock
    private AvantageSocialService avantageSocialService;

    @InjectMocks
    private Av_SocialController avSocialController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateAvantageSocial() {
        Av_Social avantageSocial = new Av_Social();
        avantageSocial.setNom("Test");
        avantageSocial.setDescription("Test description");
        avantageSocial.setMontant(BigDecimal.TEN);

        ResponseEntity<String> response = avSocialController.createAvantageSocial(avantageSocial);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Avantage social créé avec succès", response.getBody());

        verify(avantageSocialService, times(1)).createAvantageSocial(avantageSocial);
    }

    @Test
    public void testGetAllAvantagesSociaux() {
        List<Av_Social> avantagesSociaux = new ArrayList<>();
        avantagesSociaux.add(new Av_Social());
        avantagesSociaux.add(new Av_Social());

        when(avantageSocialService.getAllAvantagesSociaux()).thenReturn(avantagesSociaux);

        ResponseEntity<List<Av_Social>> response = avSocialController.getAllAvantagesSociaux();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(avantagesSociaux, response.getBody());
    }

    @Test
    public void testUpdateAvantageSocial() {
        int id = 1;
        Av_Social avantageSocial = new Av_Social();
        avantageSocial.setId(id);
        avantageSocial.setNom("Updated Test");
        avantageSocial.setDescription("Updated Test description");
        avantageSocial.setMontant(BigDecimal.valueOf(20));

        ResponseEntity<String> response = avSocialController.updateAvantageSocial(id, avantageSocial);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Avantage social mis à jour avec succès", response.getBody());

        verify(avantageSocialService, times(1)).updateAvantageSocial(avantageSocial);
    }

    @Test
    public void testDeleteAvantageSocial() {
        int id = 1;

        ResponseEntity<String> response = avSocialController.deleteAvantageSocial(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Avantage social supprimé avec succès", response.getBody());

        verify(avantageSocialService, times(1)).deleteAvantageSocial(id);
    }
}
