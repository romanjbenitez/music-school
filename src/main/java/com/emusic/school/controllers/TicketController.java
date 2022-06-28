package com.emusic.school.controllers;

import com.emusic.school.dtos.MerchTicketDTO;
import com.emusic.school.models.*;
import com.emusic.school.repositories.CourseRepository;
import com.emusic.school.repositories.CourseTicketRepository;
import com.emusic.school.repositories.MerchRepository;
import com.emusic.school.repositories.PurchaseOrderRepository;
import com.emusic.school.services.ClientService;
import com.emusic.school.services.CourseService;
import com.emusic.school.services.MerchService;
import com.emusic.school.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@RequestMapping("/api")
public class TicketController {
    @Autowired
    TicketService ticketService;
    @Autowired
    ClientService clientService;
    @Autowired
    MerchService merchService;
    @Autowired
    CourseService courseService;

    @PostMapping("/ticket_transaction")
    public ResponseEntity<?> ticketTransaction(Authentication authentication, @RequestParam  List<Long> idsCourses,
                                               @RequestBody List<MerchTicketDTO> merchTicketDTOS){

        Client client = clientService.getClientByEmail(authentication.getName());
        AtomicReference<Double> totalPrice = new AtomicReference<>(0D);

        if (idsCourses.isEmpty() && merchTicketDTOS.isEmpty()){
            return new ResponseEntity<>("MISSING DATA", HttpStatus.FORBIDDEN);
        }
        boolean invalidData = merchTicketDTOS.stream().anyMatch(merchTicketDTO -> merchTicketDTO.getId() == null || merchTicketDTO.getQuantity()<0);
        if(invalidData){
            return new ResponseEntity<>("DATA INVALID", HttpStatus.FORBIDDEN);
        }

        boolean invalidDataMerchId = merchTicketDTOS.stream().anyMatch(merchTicketDTO ->
                merchService.findByID(merchTicketDTO.getId()) == null);
        if(invalidDataMerchId){
            return new ResponseEntity<>("ID MERCH INVALID", HttpStatus.FORBIDDEN);
        }

        boolean invalidDataCourseId = idsCourses.stream().anyMatch(idCourse -> idCourse == null || courseService.getCourseById(idCourse) == null);
        if (invalidDataCourseId){
            return new ResponseEntity<>("ID COURSE INVALID", HttpStatus.FORBIDDEN);
        }

        boolean invalidDataMerchStock = merchTicketDTOS.stream().anyMatch(merchTicketDTO ->
                merchService.findByID(merchTicketDTO.getId()).getStock() < merchTicketDTO.getQuantity());
        if (invalidDataMerchStock){
            return new ResponseEntity<>("DONT HAVE THIS STOCK", HttpStatus.FORBIDDEN);
        }

        idsCourses.forEach(idCourse -> {
            totalPrice.set(totalPrice.get() + courseService.getCourseById(idCourse).getPrice());
        });

        merchTicketDTOS.forEach(merchTicketDTO -> {
            totalPrice.set(totalPrice.get() + (merchService.findByID(merchTicketDTO.getId()).getPrice() * merchTicketDTO.getQuantity()));
            merchService.findByID(merchTicketDTO.getId()).setStock(merchService.findByID(merchTicketDTO.getId()).getStock() - merchTicketDTO.getQuantity());
            merchService.saveMerch(merchService.findByID(merchTicketDTO.getId()));
        });

        Ticket ticket = new Ticket(totalPrice.get(), client);
        ticketService.saveTicket(ticket);

        idsCourses.forEach(idCourse -> {
            courseService.saveTicketCourse(courseService.getCourseById(idCourse), ticket);
        });

        merchTicketDTOS.forEach(merchTicketDTO -> {
            merchService.saveTicketMerch(merchService.findByID(merchTicketDTO.getId()), ticket);
        });

        return new ResponseEntity<>("COMPLETE", HttpStatus.OK);
    }
}
