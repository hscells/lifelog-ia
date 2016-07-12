package hscells.LifelogIa.mapper;

import hscells.LifelogIa.model.AssessmentImage;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Harry Scells on 2/07/2016.
 */
public class AssessmentImageMapper implements ResultSetMapper<AssessmentImage> {

    @Override
    public AssessmentImage map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        return new AssessmentImage(r.getInt("id"), r.getString("name"), r.getString("data"), r.getString("concept"), r.getInt("concept_id"));
    }

}
