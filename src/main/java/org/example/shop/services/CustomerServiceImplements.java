package org.example.shop.services;


import org.example.shop.models.Customer;
import org.example.shop.helpers.CustomerHelper;
import org.example.shop.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImplements implements AppService {
    private final CustomerHelper customerHelper;
    private final CustomerRepository customerRepository;

    public CustomerServiceImplements(CustomerHelper customerHelper, CustomerRepository customerRepository) {
        this.customerHelper = customerHelper;
        this.customerRepository = customerRepository;
    }

    @Override
    public boolean add() {
        Optional<Customer> optionalCustomer = customerHelper.create();
        if (optionalCustomer.isEmpty()) {
            return false;
        }
        customerRepository.save(optionalCustomer.get());
        return true;
    }

    @Override
    public boolean edit() {
        Optional<Customer> optionalModifyCustomer = this.getCustomerForModify();
        if (optionalModifyCustomer.isEmpty()) {
            return false;
        }
        Optional<Customer> optionalModifiedCustomer = customerHelper.update(optionalModifyCustomer.get());
        if (optionalModifiedCustomer.isPresent()) {
            customerRepository.save(optionalModifiedCustomer.get());
            return true;
        }
        return false;
    }

    private Optional<Customer> getCustomerForModify() {
        Optional<Long> optionalCustomerId = customerHelper.getIdModifyCustomer(customerRepository.findAll(), true);
        return optionalCustomerId.flatMap(customerRepository::findById);
    }

    @Override
    public boolean print() {
        return customerHelper.printList(customerRepository.findAll(), true);
    }
}
