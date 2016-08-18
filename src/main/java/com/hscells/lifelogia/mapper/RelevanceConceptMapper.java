package hscells.lifelogia.mapper;

import hscells.lifelogia.model.RelevanceConcept;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Harry Scells on 1/08/2016.
 */
public class RelevanceConceptMapper implements ResultSetMapper<RelevanceConcept> {
    @Override
    public RelevanceConcept map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        return new RelevanceConcept(r.getInt("id"), r.getString("value"));
    }
}
