package com.example.mock.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tripId;

    @Column
    private int bookedTicketNumber;

    @Column(length = 11)
    private String carType;

    @Column
    private Date departureDate;

    @Column
    private Time departureTime;

    @Column(length = 50)
    private String destination;

    @Column(length = 11)
    private String driver;

    @Column
    private int maximumOnlineTicketNumber;
}
