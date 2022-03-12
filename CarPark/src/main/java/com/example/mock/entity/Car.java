package com.example.mock.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Car {
    @Id
    @Column(length = 50)
    private String licensePlate;

    @Column(length = 11)
    private String carColor;

    @Column(length = 50)
    private String carType;

    @Column(length = 50)
    private String company;

    @ManyToOne
    @JoinColumn(name = "parkId")
    private ParkingLot parkingLot;
}
