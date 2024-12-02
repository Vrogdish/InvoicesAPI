package com.Invoices.myApi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Invoices.myApi.entities.CustomerEntity;
import com.Invoices.myApi.errors.RessourceNotFoundException;
import com.Invoices.myApi.models.Customer;
import com.Invoices.myApi.models.CustomerToCreate;
import com.Invoices.myApi.repositories.CustomersRepository;
import com.Invoices.myApi.security.AuthService;

@Service
public class CustomersService {

    @Autowired
    private CustomersRepository customerRepository;

    @Autowired
    private AuthService authService;

    public List<Customer> getCustomers() {
        List<CustomerEntity> customersEntities = customerRepository.findByUid(authService.getUserId());

        if (customersEntities.isEmpty()) {
            throw new RessourceNotFoundException("No customers found");
        }

        return customersEntities.stream().map(customerEntity -> new Customer(
                customerEntity.getId(),
                customerEntity.getUid(),
                customerEntity.getCompany(),
                customerEntity.getCivility(),
                customerEntity.getFirstname(),
                customerEntity.getLastname(),
                customerEntity.getEmail(),
                customerEntity.getPhone(),
                customerEntity.getStreetNumber(),
                customerEntity.getRoute(),
                customerEntity.getLocality(),
                customerEntity.getPostalCode(),
                customerEntity.getCountry(),
                customerEntity.getPlaceId())).toList();
    }

    public Customer getCustomer(long id) {
        CustomerEntity customerEntity = customerRepository.findById(id).orElse(null);

        if (customerEntity == null || customerEntity.getUid() != authService.getUserId()) {
            throw new RessourceNotFoundException("Customer with id" + id + " not found");
        } else {
            return new Customer(
                    customerEntity.getId(),
                    customerEntity.getUid(),
                    customerEntity.getCompany(),
                    customerEntity.getCivility(),
                    customerEntity.getFirstname(),
                    customerEntity.getLastname(),
                    customerEntity.getEmail(),
                    customerEntity.getPhone(),
                    customerEntity.getStreetNumber(),
                    customerEntity.getRoute(),
                    customerEntity.getLocality(),
                    customerEntity.getPostalCode(),
                    customerEntity.getCountry(),
                    customerEntity.getPlaceId());
        }
    }

    public Customer createCustomer(CustomerToCreate customer) {
        CustomerEntity customerEntity = CustomerEntity.builder()
                .uid(authService.getUserId())
                .company(customer.company())
                .civility(customer.civility())
                .firstname(customer.firstname())
                .lastname(customer.lastname())
                .email(customer.email())
                .phone(customer.phone())
                .streetNumber(customer.streetNumber())
                .route(customer.route())
                .locality(customer.locality())
                .postalCode(customer.postalCode())
                .country(customer.country())
                .placeId(customer.placeId())
                .build();

        customerRepository.save(customerEntity);

        return new Customer(
                customerEntity.getId(),
                customerEntity.getUid(),
                customerEntity.getCompany(),
                customerEntity.getCivility(),
                customerEntity.getFirstname(),
                customerEntity.getLastname(),
                customerEntity.getEmail(),
                customerEntity.getPhone(),
                customerEntity.getStreetNumber(),
                customerEntity.getRoute(),
                customerEntity.getLocality(),
                customerEntity.getPostalCode(),
                customerEntity.getCountry(),
                customerEntity.getPlaceId());
    }

    public Customer updateCustomer(long id, CustomerToCreate customer) {
        CustomerEntity customerEntity = customerRepository.findById(id).orElse(null);

        if (customerEntity == null || customerEntity.getUid() != authService.getUserId()) {
            throw new RessourceNotFoundException("Customer with id" + id + " not found");
        } else {
            customerEntity.setCompany(customer.company());
            customerEntity.setCivility(customer.civility());
            customerEntity.setFirstname(customer.firstname());
            customerEntity.setLastname(customer.lastname());
            customerEntity.setEmail(customer.email());
            customerEntity.setPhone(customer.phone());
            customerEntity.setStreetNumber(customer.streetNumber());
            customerEntity.setRoute(customer.route());
            customerEntity.setLocality(customer.locality());
            customerEntity.setPostalCode(customer.postalCode());
            customerEntity.setCountry(customer.country());
            customerEntity.setPlaceId(customer.placeId());

            customerRepository.save(customerEntity);

            return new Customer(
                    customerEntity.getId(),
                    customerEntity.getUid(),
                    customerEntity.getCompany(),
                    customerEntity.getCivility(),
                    customerEntity.getFirstname(),
                    customerEntity.getLastname(),
                    customerEntity.getEmail(),
                    customerEntity.getPhone(),
                    customerEntity.getStreetNumber(),
                    customerEntity.getRoute(),
                    customerEntity.getLocality(),
                    customerEntity.getPostalCode(),
                    customerEntity.getCountry(),
                    customerEntity.getPlaceId());
        }
    }

    public String deleteCustomer(long id) {
        CustomerEntity customerEntity = customerRepository.findById(id).orElse(null);

        if (customerEntity == null || customerEntity.getUid() != authService.getUserId()) {
            throw new RessourceNotFoundException("Customer with id" + id + " not found");
        } else {
            customerRepository.delete(customerEntity);
            return "Customer with id " + id + " deleted";
        }
    }

}
