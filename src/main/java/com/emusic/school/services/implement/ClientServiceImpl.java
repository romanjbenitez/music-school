package com.emusic.school.services.implement;

import com.emusic.school.dtos.ClientDTO;
import com.emusic.school.models.Client;
import com.emusic.school.repositories.ClientRepository;
import com.emusic.school.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    ClientRepository clientRepository;

    @Override
    public Client getClientByEmail(String email) {return clientRepository.findByEmail(email);}

    @Override
    public List<ClientDTO> getClientsDTO () {return clientRepository.findAll().stream().map(ClientDTO::new).collect(Collectors.toList());}

    @Override
    public void saveClient(Client client) {clientRepository.save(client);}
}
