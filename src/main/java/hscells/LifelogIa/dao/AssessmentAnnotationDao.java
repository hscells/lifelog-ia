package hscells.LifelogIa.dao;

import hscells.LifelogIa.mapper.ImageMapper;
import hscells.LifelogIa.model.Image;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

/**
 * Created by Harry Scells on 2/07/2016.
 */
public interface AssessmentAnnotationDao {

    @SqlQuery("SELECT id, name, data FROM images WHERE id NOT IN (SELECT image_id FROM annotated_assessment_images) ORDER BY random() LIMIT 1")
    @Mapper(ImageMapper.class)
    Image getImage();



    @SqlUpdate("INSERT INTO annotated_assessment_images (image_id, person_id, annotation, relevance) VALUES (:imageId, :personId, :conceptId, :relevance)")
    void annotate(@Bind("imageId") int imageId, @Bind("personId") int personId, @Bind("conceptId") int conceptId, @Bind("relevance") int relevance);

}
