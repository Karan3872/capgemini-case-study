package com.carwash.customer_service.service;


import com.carwash.customer_service.model.Customer;
import com.carwash.customer_service.repository.CustomerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    CustomerDAO customerDAO;


    public List<Customer> displayAllCustomers() {
        return customerDAO.findAll();
    }

    public Boolean addCustomer(Customer customer) {
        String email = customer.getEmail();
        if(customerDAO.findByEmail(email).isPresent()) return false;
        customerDAO.save(customer);
        return true;
    }

    public Optional<Customer> getCustomerById(int id) {
        return customerDAO.findById(id);
    }

    public void deleteCustomer(int id) {
        if (!customerDAO.existsById(id)) {
            throw new RuntimeException("Car not found with id: " + id);
        }
        customerDAO.deleteById(id);
    }



}
