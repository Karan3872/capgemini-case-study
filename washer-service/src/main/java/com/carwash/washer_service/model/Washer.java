package com.carwash.washer_service.model;


import com.carwash.washer_service.dto.WasherDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
public class Washer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long washerId;

    private String washerName;
    private String phoneNo;

    @Column(unique = true)
    private String email;

    public Washer(WasherDTO washerDTO){
        this.washerName= washerDTO.washerName;
        this.phoneNo=washerDTO.phoneNo;
        this.email=washerDTO.email;
    }
}
