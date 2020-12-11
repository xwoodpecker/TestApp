package com.javainuse;


import javax.persistence.*;
import java.security.Principal;

@Entity
@Table(name = "USERS")
public class User {//implements Principal {


    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String userName;

    /**
     * Default constructor for JPA only.
     */
    public User() {

    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Set JPA id - for testing and JPA only. Not intended for normal use.
     *
     * @param id The new id.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets userName.
     *
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets userName.
     *
     * @param name the userName
     */
    public void setName(String name) {
        this.userName = userName;
    }



    @Override
    public String toString() {
        return userName;
    }

    /** for principal
    @Override
    public String getName() {
        return userName;
    }**/

}
