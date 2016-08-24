package com.hscells.lifelogia.mapper.stats;

import com.hscells.lifelogia.model.stats.AnnotationStatistics;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Harry Scells on 24/08/2016.
 */
public class AnnotationStatisticsMapper implements ResultSetMapper<AnnotationStatistics> {
    @Override
    public AnnotationStatistics map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        return new AnnotationStatistics(null, r.getString("count"), r.getString("sum"), r.getString("avg"));
    }
}
