package com.carwash.washer_service.repository;

import com.carwash.washer_service.model.Washer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WasherDAO extends JpaRepository<Washer,Long> {


    Optional<Object> findByEmail(String email);
}
