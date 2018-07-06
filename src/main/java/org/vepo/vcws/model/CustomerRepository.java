package org.vepo.vcws.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

/**
 * Customer Repository
 * 
 * @author
 */
@ApplicationScoped
public class CustomerRepository {
    private Map<Long, Customer> customers = new HashMap<>();

    /**
     * Setup the Repository
     */
    @PostConstruct
    public void setup() {
        Arrays.asList(new Customer(1l, "Abraham", "Lincoln"), new Customer(2l, "George", "Washington"),
                new Customer(3l, "John", "Adams")).forEach(c -> customers.put(c.getId(), c));
    }

    /**
     * Return all Customer
     * 
     * @return All Customers
     */
    public Stream<Customer> findAll() {
        return customers.values().stream().map(c -> (Customer) c.clone());
    }

    /**
     * Find Customer by Id
     * 
     * @param id The customer id
     */
    public Optional<Customer> findById(Long id) {
        return Optional.ofNullable(customers.get(id));
    }

    /**
     * Save the current customer
     * 
     * @param customer The costumer
     */
    public void save(Customer customer) {
        if (customer.getId() == null) {
            // set new id
            customer.setId(customers.keySet().stream().mapToLong(l -> l).max().orElse(0l) + 1l);
        }
        customers.put(customer.getId(), customer);
    }
}