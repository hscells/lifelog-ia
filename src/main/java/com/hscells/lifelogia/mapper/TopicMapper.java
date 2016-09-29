package com.hscells.lifelogia.mapper;

import com.hscells.lifelogia.model.Topic;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Harry Scells on 28/06/2016.
 */
public class TopicMapper implements ResultSetMapper<Topic> {

    @Override
    public Topic map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        return new Topic(r.getString("topic_id"), r.getString("title"), r.getString("description"), r.getString("narrative"));
    }
}
