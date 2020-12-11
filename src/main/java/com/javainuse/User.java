package com.javainuse;


import javax.persistence.*;
import java.security.Principal;

/**
 * The type User.
 */
@Entity
@Table(name = "USERS")
public class User {//implements Principal {


    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String userName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "is_coordinator")
    private Boolean isCoordinator;

    @Column(name = "is_supervisor")
    private Boolean isSupervisor;


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
     * Sets user name.
     *
     * @param userName the user name
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets coordinator.
     *
     * @return the coordinator
     */
    public Boolean getCoordinator() {
        return isCoordinator;
    }

    /**
     * Sets coordinator.
     *
     * @param coordinator the coordinator
     */
    public void setCoordinator(Boolean coordinator) {
        isCoordinator = coordinator;
    }

    /**
     * Gets supervisor.
     *
     * @return the supervisor
     */
    public Boolean getSupervisor() {
        return isSupervisor;
    }

    /**
     * Sets supervisor.
     *
     * @param supervisor the supervisor
     */
    public void setSupervisor(Boolean supervisor) {
        isSupervisor = supervisor;
    }

    /**
     * Sets userName.
     *
     * @param userName the userName
     */
    public void setName(String userName) {
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
