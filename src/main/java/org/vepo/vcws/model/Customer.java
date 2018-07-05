package org.vepo.vcws.model;

import org.bson.types.ObjectId;
import org.jnosql.artemis.Column;
import org.jnosql.artemis.Entity;
import org.jnosql.artemis.Id;

/**
 * Customer
 * 
 * @author Victor Osório
 */
@Entity
public class Customer {

    @Id
    private ObjectId id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    public Customer() {
    }

    /**
     * @return the id
     */
    public ObjectId getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(ObjectId id) {
        this.id = id;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format("Customer {\n id: %s\n, firstName: %s\n, lastName: %s\n }", this.id, this.firstName,
                this.lastName);
    }

}