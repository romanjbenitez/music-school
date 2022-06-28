package com.emusic.school.controllers;

import com.emusic.school.dtos.ClientDTO;
import com.emusic.school.models.Client;
import com.emusic.school.repositories.ClientRepository;
import com.emusic.school.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/clients")
    public List<ClientDTO> getClients(){return clientService.getClientsDTO();}

    @PostMapping("/clients")
    public ResponseEntity<Object> register(
            @RequestParam String firstName, @RequestParam String lastName,
            @RequestParam String email, @RequestParam String password) {

        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty()){
            return new ResponseEntity<>("Fill in all the fields.", HttpStatus.FORBIDDEN);
        }

        if (clientService.getClientByEmail(email) != null){
            return new ResponseEntity<>("Email in use.", HttpStatus.FORBIDDEN);
        }

        if(!email.contains("@")){
            return new ResponseEntity<>("Email invalid.", HttpStatus.FORBIDDEN);
        }

        Client newClient = new Client(firstName,lastName,email,passwordEncoder.encode(password),true);
        clientService.saveClient(newClient);
        return new ResponseEntity<>("Successfully registered.", HttpStatus.CREATED);

    }

    @GetMapping("/client/current")
    public ClientDTO getCurrent(Authentication authentication){
        Client client = clientService.getClientByEmail(authentication.getName());
        return new ClientDTO(client);
    }


}
