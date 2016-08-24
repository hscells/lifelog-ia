package com.hscells.lifelogia.dao.stats;

import com.hscells.lifelogia.mapper.stats.AnnotationStatisticsMapper;
import com.hscells.lifelogia.model.stats.AnnotationStatistics;
import com.hscells.lifelogia.model.stats.Statistic;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

/**
 * Created by Harry Scells on 24/08/2016.
 */
public interface StatisticsDao {

    @SqlQuery("SELECT count(*), avg(end_time - start_time), sum(end_time - start_time) FROM annotated_text_images WHERE person_id = :personId")
    @Mapper(AnnotationStatisticsMapper.class)
    AnnotationStatistics getTextStatisticsForPerson(@Bind("personId") int personId);

    @SqlQuery("SELECT count(*), avg(end_time - start_time), sum(end_time - start_time) FROM annotated_tag_images WHERE person_id = :personId")
    @Mapper(AnnotationStatisticsMapper.class)
    AnnotationStatistics getTagStatisticsForPerson(@Bind("personId") int personId);

    @SqlQuery("SELECT count(*), avg(end_time - start_time), sum(end_time - start_time) FROM annotated_query_images WHERE person_id = :personId")
    @Mapper(AnnotationStatisticsMapper.class)
    AnnotationStatistics getQueryStatisticsForPerson(@Bind("personId") int personId);

    @SqlQuery("SELECT count(*), avg(end_time - start_time), sum(end_time - start_time) FROM annotated_assessment_images WHERE person_id = :personId")
    @Mapper(AnnotationStatisticsMapper.class)
    AnnotationStatistics getAssessmentStatisticsForPerson(@Bind("personId") int personId);

    @SqlQuery("SELECT alias FROM people WHERE id = :personId")
    String getPersonName(@Bind("personId") int personId);

}
