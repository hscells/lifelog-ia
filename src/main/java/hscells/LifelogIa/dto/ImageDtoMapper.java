package hscells.LifelogIa.dto;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Harry Scells on 17/12/2015.
 */
public class ImageDtoMapper implements ResultSetMapper<ImageDto>
{
    public ImageDto map(int index, ResultSet r, StatementContext ctx) throws SQLException
    {
        return new ImageDto(r.getInt("id"), r.getInt("chunk_id"), r.getString("name"), r.getString("data"), r.getBoolean("annotated"));
    }
}
