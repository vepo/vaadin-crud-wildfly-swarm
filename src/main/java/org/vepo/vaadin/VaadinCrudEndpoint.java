package org.vepo.vaadin;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/")
public class VaadinCrudEndpoint {

    @GET
    public String hello() {
        return "Hello World";
    }
}