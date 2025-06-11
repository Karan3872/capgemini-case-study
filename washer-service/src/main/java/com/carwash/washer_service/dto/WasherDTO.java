package com.carwash.washer_service.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WasherDTO {

    public String washerName;
    public String phoneNo;
    public String email;

}
