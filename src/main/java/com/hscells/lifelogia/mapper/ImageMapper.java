package com.hscells.lifelogia.mapper;

import com.hscells.lifelogia.model.Image;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Harry Scells on 17/12/2015.
 */
public class ImageMapper implements ResultSetMapper<Image> {
    public Image map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        return new Image(r.getInt("id"), r.getString("name"), r.getString("data"));
    }
}
