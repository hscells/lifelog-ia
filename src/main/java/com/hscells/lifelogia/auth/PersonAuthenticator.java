package com.hscells.lifelogia.auth;

import com.hscells.lifelogia.model.Person;
import com.hscells.lifelogia.service.PersonService;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;

import java.util.Optional;

/**
 * Created by Harry Scells on 28/06/2016.
 */
public class PersonAuthenticator implements Authenticator<BasicCredentials, Person> {

    private PersonService personService;

    public PersonAuthenticator(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public Optional<Person> authenticate(BasicCredentials credentials) throws AuthenticationException {
        if (personService.loginPerson(credentials.getUsername(), credentials.getPassword())) {
            return Optional.of(personService.getPersonByAlias(credentials.getUsername()));
        }

        return Optional.empty();
    }

}