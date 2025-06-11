package com.carwash.customer_service.controller;


import com.carwash.customer_service.dto.CustomerDTO;
import com.carwash.customer_service.model.Customer;
import com.carwash.customer_service.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;


@RestController
@RequestMapping("customer")
public class customerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/allCustomers")
    public ResponseEntity<List<Customer>> displayAllCustomers(){
        List<Customer> customers = customerService.displayAllCustomers();
        if(customers!=null || !customers.isEmpty()){
            return ResponseEntity.ok(customers);
        }
        else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/getbyid/{customer_id}")
    public ResponseEntity<Optional<Customer>> getCustomerById(@PathVariable int id)
    {
        Optional<Customer> customer = customerService.getCustomerById(id);
        if (customer != null) {
            return ResponseEntity.ok(customer);

        } else {
            return ResponseEntity.notFound().build(); // Car not found
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Boolean> addCustomer(@RequestBody CustomerDTO customerDTO){
        Customer customer = new Customer(customerDTO);
        boolean result =  customerService.addCustomer(customer);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{customer_id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable int id) {
        customerService.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
