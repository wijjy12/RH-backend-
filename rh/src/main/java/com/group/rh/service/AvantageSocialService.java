package com.group.rh.service;

import com.group.rh.entity.Av_Social;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Transactional

    public interface AvantageSocialService {

        void createAvantageSocial(Av_Social avantageSocial);
        List<Av_Social> getAllAvantagesSociaux();
        void updateAvantageSocial(Av_Social avantageSocial);
        void deleteAvantageSocial(int id);
    }


