package com.carwash.customer_service.repository;


import com.carwash.customer_service.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerDAO extends JpaRepository<Customer,Integer> {

    Optional<Object> findByEmail(String email);

}
