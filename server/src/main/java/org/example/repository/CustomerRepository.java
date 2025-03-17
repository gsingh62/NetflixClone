package org.example.repository;

import org.springframework.data.repository.CrudRepository;
import org.example.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, String> {
}