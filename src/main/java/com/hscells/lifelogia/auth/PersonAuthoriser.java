package hscells.lifelogia.auth;

import hscells.lifelogia.model.Person;
import io.dropwizard.auth.Authorizer;

/**
 * Created by Harry Scells on 28/06/2016.
 */
public class PersonAuthoriser implements Authorizer<Person> {

    @Override
    public boolean authorize(Person principal, String role) {
        return principal.getRole().equals(role);
    }
}
