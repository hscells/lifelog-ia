package com.hscells.lifelogia.service;

import com.hscells.lifelogia.dao.PersonDao;
import com.hscells.lifelogia.model.Person;
import org.jasypt.util.password.StrongPasswordEncryptor;

/**
 * Created by Harry Scells on 28/06/2016.
 */
public class PersonService {

    private PersonDao personDao;

    private StrongPasswordEncryptor passwordEncryptor;

    public PersonService(PersonDao personDao) {
        this.personDao = personDao;
        passwordEncryptor = new StrongPasswordEncryptor();
    }

    public Person getPersonByAlias(String alias) {
        return personDao.getPersonByAlias(alias);
    }

    public boolean loginPerson(String alias, String password) {

        Person dbPerson = personDao.getPersonByAlias(alias);
        return !(dbPerson == null || !dbPerson.isApproved()) && passwordEncryptor.checkPassword(password, dbPerson.getEncryptedPassword());

    }

    public boolean registerPerson(String alias, String email, String password) {

        Person person = personDao.getPersonByAlias(alias);
        if (person != null || alias.isEmpty() || email.isEmpty() || password.isEmpty()) {
            return false;
        }
        String encryptedPassword = passwordEncryptor.encryptPassword(password);
        personDao.registerPerson(alias, email, encryptedPassword);
        return true;

    }

}
