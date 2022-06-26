package com.emusic.school.services.implement;

import com.emusic.school.dtos.MerchDTO;
import com.emusic.school.models.Merch;
import com.emusic.school.repositories.MerchRepository;
import com.emusic.school.services.MerchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MerchServiceImpl implements MerchService {
    @Autowired
    MerchRepository merchRepository;

    @Override
    public List<MerchDTO> getMerch() {
        return merchRepository.findAll().stream().filter(merch -> merch.isActive()).map(merch -> new MerchDTO(merch)).collect(Collectors.toList());
    }

    @Override
    public void saveMerch(Merch merch) {
        merchRepository.save(merch);
    }

    @Override
    public Merch findByID(long id) {
        return merchRepository.findById(id).orElse(null);
    }
}
