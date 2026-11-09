package com.example.ServiceDeskV1.dto.request;

import com.example.ServiceDeskV1.entity.Priority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record TicketRequest(

        @NotBlank(message = "Tytuł jest wymagany")
        @Size(max = 150, message = "Tytuł może mieć maksymalnie 150 znaków")
        String title,

        @NotBlank(message = "Opis jest wymagany")
        @Size(max = 5000, message = "Opis może mieć maksymalnie 5000 znaków")
        String description,

        @NotNull(message = "Priorytet jest wymagany")
        Priority priority

) {
}
