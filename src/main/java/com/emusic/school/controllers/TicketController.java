package com.emusic.school.controllers;

import com.emusic.school.models.*;
import com.emusic.school.repositories.CourseRepository;
import com.emusic.school.repositories.CourseTicketRepository;
import com.emusic.school.repositories.MerchRepository;
import com.emusic.school.repositories.PurchaseOrderRepository;
import com.emusic.school.services.ClientService;
import com.emusic.school.services.CourseService;
import com.emusic.school.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    CourseRepository courseRepository;
    @Autowired
    MerchRepository merchRepository;

    @Autowired
    PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    CourseService courseService;
    @PostMapping("/ticket_transaction")
    public ResponseEntity<?> ticketTransaction(Authentication authentication){
        List<Course> courses = courseRepository.findAll();
        List<Merch> merches = merchRepository.findAll();
        Client client = clientService.getClientByEmail(authentication.getName());
        AtomicReference<Double> totalPrice = new AtomicReference<>(0D);
        if(courses.size() == 0 && merches.size() == 0){
            return new ResponseEntity<>("MISSING DATA", HttpStatus.FORBIDDEN);
        }
        if(courses.size()>0){
            courses.stream().forEach(course -> {
                totalPrice.set(totalPrice.get() + course.getPrice());
            });
       }
        if(merches.size()>0){
            merches.stream().forEach(merch -> {
                totalPrice.set(totalPrice.get() + merch.getPrice());
            });
        }
        Ticket ticket = new Ticket(totalPrice.get(), client);
        ticketService.saveTicket(ticket);
        if(courses.size()>0){
            courses.stream().forEach(course -> {courseService.saveTicketCourse(course, ticket);
            });
        }
        if(merches.size()>0){
            merches.stream().forEach(merch -> {
                PurchaseOrder purchaseOrder = new PurchaseOrder(ticket, merch);
                purchaseOrderRepository.save(purchaseOrder);
            });
        }



        return new ResponseEntity<>("", HttpStatus.OK);
    }
}
