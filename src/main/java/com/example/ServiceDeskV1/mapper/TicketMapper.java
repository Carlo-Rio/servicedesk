package com.example.ServiceDeskV1.mapper;

import com.example.ServiceDeskV1.dto.response.TicketResponse;
import com.example.ServiceDeskV1.entity.AppUser;
import com.example.ServiceDeskV1.entity.Ticket;

public final class TicketMapper {

    private TicketMapper() {
    }

    public static TicketResponse toResponse(Ticket ticket) {
        AppUser assignee = ticket.getAssignee();

        return new TicketResponse(
                ticket.getId(),
                ticket.getTitle(),
                ticket.getDescription(),
                ticket.getStatus(),
                ticket.getPriority(),
                ticket.getCustomer().getId(),
                assignee != null ? assignee.getId() : null,
                ticket.getVersion(),
                ticket.getCreatedAt(),
                ticket.getUpdatedAt()
        );
    }
}