package com.hscells.lifelogia.mapper;

import com.hscells.lifelogia.model.Leaderboard;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Harry Scells on 11/01/2016.
 */
public class LeaderboardMapper implements ResultSetMapper<Leaderboard>{

    public Leaderboard map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        return new Leaderboard(r.getString("name"),r.getInt("num_annotated"));
    }
}
