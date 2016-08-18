package hscells.lifelogia.dao;

import hscells.lifelogia.mapper.ImageMapper;
import hscells.lifelogia.mapper.RelevanceConceptMapper;
import hscells.lifelogia.model.Image;
import hscells.lifelogia.model.RelevanceConcept;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import java.util.List;

/**
 * Created by Harry Scells on 2/07/2016.
 */
public interface AssessmentAnnotationDao {

    @SqlQuery("SELECT id, name, data FROM images WHERE id NOT IN (SELECT image_id FROM annotated_assessment_images) ORDER BY random() LIMIT 1")
    @Mapper(ImageMapper.class)
    Image getImage();

    @SqlQuery("SELECT DISTINCT concepts.id, concepts.value FROM assessment_concepts concepts JOIN annotated_assessment_images ON concepts.id NOT IN (SELECT annotation FROM annotated_assessment_images WHERE image_id = :imageId) LIMIT 10;")
    @Mapper(RelevanceConceptMapper.class)
    List<RelevanceConcept> getConceptsForImage(@Bind("imageId") int imageId);

    @SqlUpdate("INSERT INTO annotated_assessment_images (image_id, person_id, annotation, relevance) VALUES (:imageId, :personId, :conceptId, :relevance)")
    void annotate(@Bind("imageId") int imageId, @Bind("personId") int personId, @Bind("conceptId") int conceptId, @Bind("relevance") int relevance);

}