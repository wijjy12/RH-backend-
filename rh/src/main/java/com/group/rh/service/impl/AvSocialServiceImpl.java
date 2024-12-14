package com.group.rh.service.impl;
import com.group.rh.entity.Av_Social;
import com.group.rh.repository.Av_SocialRepository;
import com.group.rh.service.AvantageSocialService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Transactional
public class AvSocialServiceImpl implements AvantageSocialService {
    final private Av_SocialRepository avantageSocialRepository;
     @Autowired
    public AvSocialServiceImpl(Av_SocialRepository avantageSocialRepository) {
        this.avantageSocialRepository = avantageSocialRepository;
    }

    @Override
    public void createAvantageSocial(Av_Social avantageSocial) {
        avantageSocialRepository.save(avantageSocial);
    }

    @Override
    public List<Av_Social> getAllAvantagesSociaux() {
        return avantageSocialRepository.findAll();
    }

    @Override
    public void updateAvantageSocial(Av_Social avantageSocial) {
        avantageSocialRepository.save(avantageSocial);
    }
    @Override
    public void deleteAvantageSocial(int id) {
        avantageSocialRepository.deleteById(id);
    }
}