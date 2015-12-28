package hscells.LifelogIa.dao;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

/**
 * Created by Harry Scells on 17/12/2015.
 */
public interface PeopleDao {

    @SqlQuery("SELECT id FROM people WHERE name = :personName")
    int getPersonIdByName(@Bind("personName") String personName);

    @SqlUpdate("INSERT INTO people (name) VALUES (:personName)")
    void addPerson(@Bind("personName") String personName);

}
