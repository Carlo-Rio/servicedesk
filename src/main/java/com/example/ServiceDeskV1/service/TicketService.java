package com.example.ServiceDeskV1.service;

import com.example.ServiceDeskV1.dto.response.TicketResponse;
import com.example.ServiceDeskV1.entity.Ticket;
import com.example.ServiceDeskV1.exception.TicketNotFoundException;
import com.example.ServiceDeskV1.mapper.TicketMapper;
import com.example.ServiceDeskV1.repository.TicketRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Transactional(readOnly = true)
    public TicketResponse getTicket(Long id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new TicketNotFoundException(id));

        return TicketMapper.toResponse(ticket);
    }
}
