package hscells.LifelogIa.dao;

import hscells.LifelogIa.mapper.LeaderboardMapper;
import hscells.LifelogIa.model.Leaderboard;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import java.util.List;

/**
 * Created by Harry Scells on 17/12/2015.
 */
public interface StatsDao {

    @SqlQuery("SELECT (SELECT count(id) as images from images) - (SELECT count(id) as annotated_images FROM annotated_images)")
    int getUnannotatedImageCount();

    @SqlQuery("SELECT people.name, count(annotated_images.id) as num_annotated FROM people, annotated_images WHERE people.id = annotated_images.person_id GROUP BY people.name ORDER BY num_annotated DESC limit 10")
    @Mapper(LeaderboardMapper.class)
    List<Leaderboard> getLeaderboardScores();

}
