package com.hscells.lifelogia.dao;

import com.hscells.lifelogia.mapper.PersonMapper;
import com.hscells.lifelogia.model.Person;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

/**
 * Created by Harry Scells on 17/12/2015.
 */
public interface PersonDao {

    @SqlQuery("SELECT id FROM people WHERE name = :personName")
    int getPersonIdByName(@Bind("personName") String personName);

    @SqlUpdate("INSERT INTO people (alias, email, password) VALUES (:alias, :email, :password)")
    void registerPerson(@Bind("alias") String alias, @Bind("email") String email, @Bind("password") String password);

    @SqlQuery("SELECT id, alias, email, password, role, approved FROM people WHERE alias = :alias")
    @Mapper(PersonMapper.class)
    Person getPersonByAlias(@Bind("alias") String alias);

    @SqlUpdate("UPDATE people SET password=:password WHERE email=:email")
    void resetPassword(@Bind("password") String password, @Bind("email") String email);


}
