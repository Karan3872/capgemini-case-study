package com.carwash.washer_service.controller;


import com.carwash.washer_service.dto.WasherDTO;
import com.carwash.washer_service.model.Washer;
import com.carwash.washer_service.service.WasherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("washer")
public class WasherController {

    @Autowired
    WasherService washerService;

    @PostMapping("/add")
    public ResponseEntity<Boolean> addWasher(@RequestBody WasherDTO washerDTO){
        Washer washer = new Washer(washerDTO);
        boolean result =  washerService.addCar(washer);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<List> getAll(){
        List<Washer> washers = washerService.getAll();
        if(washers!=null && !washers.isEmpty()){
            return ResponseEntity.ok(washers);
        }
        return ResponseEntity.noContent().build(); // no car found
    }

    @PostMapping("/get{id}")
    public ResponseEntity<Optional<Washer>> getById(@PathVariable long id){
        Optional<Washer> washer = washerService.getById(id);
        if (washer!=null && !washer.isEmpty()){
            return ResponseEntity.ok(washer);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update{id}")
    public ResponseEntity<Washer> updateById(@PathVariable long id,@RequestBody WasherDTO washerDTO){
        Washer updateWasher = washerService.updateById(id,washerDTO);
        return new ResponseEntity<>(updateWasher,HttpStatus.OK);
    }

    @DeleteMapping("/delete{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id){
         washerService.deleteById(id);
         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
