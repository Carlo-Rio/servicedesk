package com.example.ServiceDeskV1.repository;

import com.example.ServiceDeskV1.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;



public interface TicketRepository extends JpaRepository<Ticket, Long> {


}
