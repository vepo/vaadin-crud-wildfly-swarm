package org.vepo.vcws.model;

import java.util.stream.Stream;

import org.bson.types.ObjectId;
import org.jnosql.artemis.Repository;

/**
 * Customer Repository
 * 
 * @author
 */
public interface CustomerRepository extends Repository<Customer, ObjectId> {
    /**
     * Return all Customer
     * 
     * @return All Customers
     */
    public Stream<Customer> findAll();
}