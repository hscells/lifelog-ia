package com.hscells.lifelogia.service;

import com.hscells.lifelogia.dao.PersonDao;
import com.hscells.lifelogia.dto.PersonDto;
import org.jasypt.util.password.StrongPasswordEncryptor;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;

/**
 * Created by Harry Scells on 29/09/2016.
 */
public class RecoveryService {

    private ConcurrentHashMap<String, String> codes = new ConcurrentHashMap<>();
    private StrongPasswordEncryptor passwordEncryptor;
    private PersonDao personDao;

    public boolean resetPassword(PersonDto details, String code) {
        if (codes.containsKey(code)) {
            String encryptedPassword = passwordEncryptor.encryptPassword(details.getPassword());
            personDao.resetPassword(details.getPassword(), details.getEmail());
        }
        return false;
    }

    public void sendResetEmail(String email) throws ExecutionException {
        //TODO
    }

}
