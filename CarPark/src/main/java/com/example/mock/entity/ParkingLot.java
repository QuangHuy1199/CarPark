package com.example.mock.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ParkingLot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long parkId;

    @Column
    private Long parkArea;

    @Column(length = 50)
    private String parkName;

    @Column(length = 11)
    private String parkPlace;

    @Column
    private Long parkPrice;

    @Column(length = 50)
    private String parkStatus;
}
