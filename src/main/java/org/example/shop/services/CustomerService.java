package org.example.shop.services;

import org.example.shop.models.Customer;
import org.example.shop.models.Product;

public interface CustomerService extends AppService<Customer> {

    Customer getCustomerById(Long id);
    void saveCustomer(Customer customer);
}