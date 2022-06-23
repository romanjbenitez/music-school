package com.emusic.school.controllers;

import com.emusic.school.dtos.ClientDTO;
import com.emusic.school.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;


    @GetMapping("/clients")
    public List<ClientDTO> getClients(){return clientRepository.findAll().stream().map(ClientDTO::new).collect(Collectors.toList());}
}
