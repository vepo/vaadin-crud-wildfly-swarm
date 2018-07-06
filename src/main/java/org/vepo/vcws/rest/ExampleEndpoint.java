package org.vepo.vcws.rest;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.vepo.vcws.model.Customer;
/**
 * @author Victor Osório
 */
@Path("")
public class ExampleEndpoint {
    @GET
    public String hello() {
        return "Hello World";
    }

    @Path("/customers")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Customer> listCustomers() {
        return Arrays.asList(new Customer(1l, "Abraham", "Lincoln"), new Customer(2l, "George", "Washington"),
                new Customer(3l, "John", "Adams"));
    }
}