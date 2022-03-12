package com.example.mock.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookingOfficeDTO {
    private Long officeId;
    private String officeName;
    private String trip;
}
