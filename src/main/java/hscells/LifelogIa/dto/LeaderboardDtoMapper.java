package hscells.LifelogIa.dto;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Harry Scells on 11/01/2016.
 */
public class LeaderboardDtoMapper implements ResultSetMapper<LeaderboardDto>{

    public LeaderboardDto map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        return new LeaderboardDto(r.getString("name"),r.getInt("num_annotated"));
    }
}
