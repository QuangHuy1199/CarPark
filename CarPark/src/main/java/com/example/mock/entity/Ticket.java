package com.example.mock.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Time;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticketId;

    @Column
    private Time bookingTime;

    @Column(length = 11)
    private String customerName;

    @ManyToOne
    @JoinColumn(name = "licensePlate")
    private Car car;

    @ManyToOne
    @JoinColumn(name = "tripId")
    private Trip trip;
}
