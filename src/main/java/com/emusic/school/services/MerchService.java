package com.emusic.school.services;

import com.emusic.school.dtos.MerchDTO;
import com.emusic.school.models.Merch;

import java.util.List;

public interface MerchService {
    List<MerchDTO> getMerch();

    void saveMerch(Merch merch);

    Merch findByID(long id);
}
