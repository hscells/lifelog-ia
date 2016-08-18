package com.hscells.lifelogia.model;

import java.security.Principal;
import java.util.UUID;

/**
 * Created by Harry Scells on 19/12/2015.
 */
public class Person implements Principal {

    private int id;
    private String alias;
    private String email;
    private String encryptedPassword;
    private String role;
    private boolean approved;

    private UUID token;

    public Person(int id, String alias, String email, String encryptedPassword, String role, boolean approved) {
        this.id = id;
        this.alias = alias;
        this.email = email;
        this.encryptedPassword = encryptedPassword;
        this.role = role;
        this.approved = approved;
    }

    public int getId() {
        return id;
    }

    public String getAlias() {
        return alias;
    }

    public String getEmail() {
        return email;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public String getRole() {
        return role;
    }

    public boolean isApproved() {
        return approved;
    }

    @Override
    public String getName() {
        return alias;
    }

    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }
}
