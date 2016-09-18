package com.hscells.lifelogia.dao;

import com.hscells.lifelogia.model.RelevanceConcept;
import com.hscells.lifelogia.mapper.ImageMapper;
import com.hscells.lifelogia.mapper.RelevanceConceptMapper;
import com.hscells.lifelogia.model.Image;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Harry Scells on 2/07/2016.
 */
public interface AssessmentAnnotationDao {

    @SqlQuery("SELECT id, name, data FROM images WHERE id IN (SELECT image_id FROM (SELECT i, image_id FROM (SELECT 1 i, image_id FROM annotated_text_images UNION (SELECT 0, id from images ORDER BY random())) as x WHERE image_id NOT IN (SELECT image_id FROM annotated_assessment_images) ORDER BY i DESC LIMIT 1) a) LIMIT 1")
    @Mapper(ImageMapper.class)
    Image getImage();

    @SqlQuery("SELECT DISTINCT concepts.id, concepts.value FROM assessment_concepts concepts JOIN annotated_assessment_images ON concepts.id NOT IN (SELECT annotation FROM annotated_assessment_images WHERE image_id = :imageId);")
    @Mapper(RelevanceConceptMapper.class)
    List<RelevanceConcept> getConceptsForImage(@Bind("imageId") int imageId);

    @SqlUpdate("INSERT INTO annotated_assessment_images (image_id, person_id, annotation, relevance, start_time, end_time) VALUES (:imageId, :personId, :conceptId, :relevance, :startTime, :endTime)")
    void annotate(@Bind("imageId") int imageId, @Bind("personId") int personId, @Bind("conceptId") int conceptId, @Bind("relevance") int relevance, @Bind("startTime") Timestamp startTime, @Bind("endTime") Timestamp endTime);

}