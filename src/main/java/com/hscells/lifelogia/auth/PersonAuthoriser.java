package com.hscells.lifelogia.auth;

import com.hscells.lifelogia.model.Person;
import io.dropwizard.auth.Authorizer;

/**
 * Created by Harry Scells on 28/06/2016.
 */
public class PersonAuthoriser implements Authorizer<Person> {

    @Override
    public boolean authorize(Person principal, String role) {
        return false;
    }
}
