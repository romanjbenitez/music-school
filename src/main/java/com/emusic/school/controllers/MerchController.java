package com.emusic.school.controllers;

import com.emusic.school.dtos.MerchDTO;
import com.emusic.school.dtos.NewMerchDTO;
import com.emusic.school.models.Merch;
import com.emusic.school.models.MerchWaist;
import com.emusic.school.repositories.MerchRepository;
import com.emusic.school.services.MerchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class MerchController {

    @Autowired
    private MerchRepository merchRepository;

    @Autowired
    private MerchService merchService;
    @GetMapping("/merch")
    public List<MerchDTO> getMerch(){
        return merchService.getMerch();
    }

    @PostMapping("/create/merch")
    public ResponseEntity<Object> createMerch(@RequestBody NewMerchDTO newMerchDTO){

        if(newMerchDTO.getStock() <=0 || newMerchDTO.getPrice() <=0 || newMerchDTO.getType().isEmpty()){
            return new ResponseEntity<>("Missing Data", HttpStatus.FORBIDDEN);
        }

        Merch merch = new Merch(newMerchDTO.getStock(),newMerchDTO.getType(),newMerchDTO.getPrice(),newMerchDTO.getWaist(),true);
        merchService.saveMerch(merch);
        return new ResponseEntity<>("Created",HttpStatus.CREATED);
    }

    @PatchMapping("/delete/merch")
    public ResponseEntity<Object> deleteMerch(@RequestParam long id){
        Merch merch = merchService.findByID(id);
        if(merch == null){
            return new ResponseEntity<>("Missing Data",HttpStatus.FORBIDDEN);
        }
        merch.setActive(false);
        merchService.saveMerch(merch);
        return new ResponseEntity<>("",HttpStatus.ACCEPTED);
    }
}
