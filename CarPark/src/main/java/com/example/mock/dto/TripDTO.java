package com.example.mock.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TripDTO {
    private Long tripId;
    private String destination;
    private Time departureTime;
    private String driver;
    private String carType;
    private int bookedTicketNumber;
}
