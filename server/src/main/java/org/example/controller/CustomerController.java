package org.example.controller;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.example.model.Customer;
import org.example.repository.CustomerRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class CustomerController
{
    @Autowired
    CustomerRepository customerRepository;
    @GetMapping(value = "/healthcheck", produces = "application/json; charset=utf-8")
    public String getHealthCheck()
    {
        return "{ \"isWorking\" : true }";
    }
    @GetMapping("/Customers")
    public List<Customer> getCustomers()
    {
        Iterable<Customer> result = customerRepository.findAll();
        List<Customer> CustomersList = new ArrayList<Customer>();
        result.forEach(CustomersList::add);
        return CustomersList;
    }
    @GetMapping("/Customer/{id}")
    public Optional<Customer> getCustomer(@PathVariable String id)
    {
        Optional<Customer> emp = customerRepository.findById(id);
        return emp;
    }
    @PutMapping("/Customer/{id}")
    public Optional<Customer> updateCustomer(@RequestBody Customer newCustomer, @PathVariable String id)
    {
        Optional<Customer> optionalEmp = customerRepository.findById(id);
        if (optionalEmp.isPresent()) {
            Customer emp = optionalEmp.get();
            emp.setFirstName(newCustomer.getFirstName());
            emp.setLastName(newCustomer.getLastName());
            emp.setEmail(newCustomer.getEmail());
            customerRepository.save(emp);
        }
        return optionalEmp;
    }
    @DeleteMapping(value = "/Customer/{id}", produces = "application/json; charset=utf-8")
    public String deleteCustomer(@PathVariable String id) {
        Boolean result = customerRepository.existsById(id);
        customerRepository.deleteById(id);
        return "{ \"success\" : "+ (result ? "true" : "false") +" }";
    }
    @PostMapping("/Customer")
    public Customer addCustomer(@RequestBody Customer newCustomer)
    {
        UUID id = UUID.randomUUID();
        Customer cust = new Customer(id, newCustomer.getFirstName(), newCustomer.getLastName(), newCustomer.getEmail());
        customerRepository.save(cust);
        return cust;
    }
}