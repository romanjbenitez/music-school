package com.emusic.school.services;

import com.emusic.school.dtos.ClientDTO;

import java.util.List;

public interface ClientService {
    List<ClientDTO> getClientsDTO();
}
