package hscells.LifelogIa.mapper;

import hscells.LifelogIa.model.Person;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Harry Scells on 28/06/2016.
 */
public class PersonMapper implements ResultSetMapper<Person> {


    @Override
    public Person map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        return new Person(r.getInt("id"), r.getString("alias"), r.getString("email"), r.getString("password"), r.getString("role"), r.getBoolean("approved"));
    }
}
