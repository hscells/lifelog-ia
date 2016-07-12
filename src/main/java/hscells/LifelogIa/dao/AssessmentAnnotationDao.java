package hscells.LifelogIa.dao;

import hscells.LifelogIa.mapper.AssessmentImageMapper;
import hscells.LifelogIa.model.AssessmentImage;
import hscells.LifelogIa.model.Image;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import java.util.List;

/**
 * Created by Harry Scells on 2/07/2016.
 */
public interface AssessmentAnnotationDao {

    /**
     *
     SELECT id, name, data, concept, conceptId
     FROM (
         SELECT id AS conceptId, value AS concept, row_number() OVER () AS row_num
         FROM assessment_concepts
     ) a
     JOIN (
         SELECT id, name, data, (row_number() OVER () % concepts.count) + 1 AS row_num
         FROM images, (SELECT count(id) AS count FROM assessment_concepts) AS concepts
         WHERE id NOT IN (SELECT image_id FROM annotated_assessment_images)
         ORDER BY random()
         LIMIT 1
     ) b USING (row_num)
     WHERE (id, conceptId) NOT IN (
         SELECT id, annotation FROM annotated_assessment_images
     )
     * @return
     */
    @SqlQuery("SELECT id, name, data, concept, concept_id FROM (SELECT id AS concept_id, value AS concept, row_number() OVER () AS row_num FROM assessment_concepts) a JOIN (SELECT id, name, data, (row_number() OVER () % concepts.count) + 1 AS row_num FROM images, (SELECT count(id) AS count FROM assessment_concepts) AS concepts WHERE id NOT IN (SELECT image_id FROM annotated_assessment_images) ORDER BY random() LIMIT 1) b USING (row_num) WHERE (id, concept_id) NOT IN (SELECT id, annotation FROM annotated_assessment_images)")
    @Mapper(AssessmentImageMapper.class)
    List<AssessmentImage> getImages();

    @SqlUpdate("INSERT INTO annotated_assessment_images (image_id, person_id, annotation, relevance) VALUES (:imageId, :personId, :conceptId, :relevance)")
    void annotate(@Bind("imageId") int imageId, @Bind("personId") int personId, @Bind("conceptId") int conceptId, @Bind("relevance") int relevance);

}
