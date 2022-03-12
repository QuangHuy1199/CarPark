package com.example.mock.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookingOffice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long officeId;

    @Column
    private Date endContractDeadline;

    @Column(length = 50)
    private String officeName;

    @Column(length = 11)
    private String officePhone;

    @Column(length = 50)
    private String officePlace;

    @Column
    private Long officePrice;

    @Column
    private Date startContractDeadline;

    @ManyToOne
    @JoinColumn(name = "tripId")
    private Trip trip;
}
