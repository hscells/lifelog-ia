package hscells.lifelogia.dao;

import hscells.lifelogia.mapper.ImageMapper;
import hscells.lifelogia.model.Image;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

/**
 * Created by Harry Scells on 28/06/2016.
 */
public interface ReverseQueryDao {

    @SqlQuery("SELECT id, name, data FROM images WHERE id NOT IN (SELECT image_id FROM annotated_query_images) ORDER BY random() LIMIT 1")
    @Mapper(ImageMapper.class)
    Image getImage();

    @SqlUpdate("INSERT INTO annotated_query_images (image_id, person_id, annotation) VALUES (:image_id, :person_id, :annotation)")
    void annotateImage(@Bind("image_id") int imageId, @Bind("person_id") int personId, @Bind("annotation") String annotation);

}