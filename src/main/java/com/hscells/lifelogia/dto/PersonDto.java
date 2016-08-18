package com.hscells.lifelogia.dto;

/**
 * Created by Harry Scells on 28/06/2016.
 */
public class PersonDto {

    private String alias;
    private String email;
    private String password;

    public PersonDto() {

    }

    public PersonDto(String alias, String email, String password) {
        this.alias = alias;
        this.email = email;
        this.password = password;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
