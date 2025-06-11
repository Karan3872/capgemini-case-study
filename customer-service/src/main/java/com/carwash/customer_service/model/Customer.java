package com.carwash.customer_service.model;


import com.carwash.customer_service.dto.CustomerDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customer_id;
    private String name;
    private String password;
    private String image;

    @Column(unique = true)
    private String email;
    private String phoneNo;


    public Customer(CustomerDTO customerDTO){
        this.name= customerDTO.getName();
        this.password=customerDTO.getPassword();
        this.image=customerDTO.getImage();
        this.email=customerDTO.getEmail();
        this.phoneNo=customerDTO.getPhoneNo();
    }



}
