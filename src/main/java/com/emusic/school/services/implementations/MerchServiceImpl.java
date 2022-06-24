package com.emusic.school.services.implementations;

import com.emusic.school.repositories.MerchRepository;
import com.emusic.school.services.MerchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MerchServiceImpl implements MerchService {
    @Autowired
    MerchRepository merchRepository;
}
