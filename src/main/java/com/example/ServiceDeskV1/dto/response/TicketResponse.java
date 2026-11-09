package com.example.ServiceDeskV1.dto.response;

import com.example.ServiceDeskV1.entity.Priority;
import com.example.ServiceDeskV1.entity.Status;

import java.time.Instant;

public record TicketResponse(
        Long id,
        String title,
        String description,
        Status status,
        Priority priority,
        Long customerId,
        Long assigneeId,
        Long version,
        Instant createdAt,
        Instant updatedAt
) {
}