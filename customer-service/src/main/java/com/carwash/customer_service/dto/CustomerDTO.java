package com.carwash.customer_service.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {

    private String name;
    private String password;
    private String image;
    private String email;
    private String phoneNo;


}
