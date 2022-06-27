package com.emusic.school.services.implement;

import com.emusic.school.models.Ticket;
import com.emusic.school.repositories.TicketRepository;
import com.emusic.school.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketServiceImpl implements TicketService {
    @Autowired
    TicketRepository ticketRepository;

    @Override
    public void saveTicket(Ticket ticket) {
        ticketRepository.save(ticket);
    }
}
