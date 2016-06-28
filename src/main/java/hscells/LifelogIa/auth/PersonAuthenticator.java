package hscells.LifelogIa.auth;

import com.google.common.base.Optional;
import hscells.LifelogIa.model.Person;
import hscells.LifelogIa.service.PersonService;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;


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

        return Optional.absent();
    }

}