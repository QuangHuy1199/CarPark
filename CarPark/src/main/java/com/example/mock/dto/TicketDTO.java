package com.example.mock.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TicketDTO {
    private Long ticketId;
    private Time bookingTime;
    private String customerName;
    private String car;
    private String trip;
}
