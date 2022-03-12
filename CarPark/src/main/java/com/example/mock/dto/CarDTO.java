package com.example.mock.dto;

import com.example.mock.entity.ParkingLot;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CarDTO {
    private String licensePlate;
    private String carColor;
    private String carType;
    private String company;
    private String parkingLot;
}
