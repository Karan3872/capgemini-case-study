package com.carwash.washer_service.service;


import com.carwash.washer_service.dto.WasherDTO;
import com.carwash.washer_service.model.Washer;
import com.carwash.washer_service.repository.WasherDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WasherService {

    @Autowired
    WasherDAO washerDAO;

    public boolean addCar(Washer washer) {
        String email = washer.getEmail();
        if(washerDAO.findByEmail(email).isPresent()){
            return false;
        }
        washerDAO.save(washer);
        return true;
    }

    public List<Washer> getAll() {
        return washerDAO.findAll();
    }

    public Optional<Washer> getById(long id) {
        return washerDAO.findById(id);
    }

    public Washer updateById(long id, WasherDTO washerDTO) {

        Washer existingWasher = washerDAO.findById(id).orElseThrow(() -> new RuntimeException("Washer is not found with id "+id));
        existingWasher.setWasherName(washerDTO.getWasherName());
        existingWasher.setPhoneNo(washerDTO.getPhoneNo());
        existingWasher.setEmail(washerDTO.getEmail());

        return washerDAO.save(existingWasher);
    }


    public void deleteById(long id) {
        if(!washerDAO.existsById(id)){
            new RuntimeException("Washer is not found with id"+id);
        }
         washerDAO.deleteById(id);
    }
}
