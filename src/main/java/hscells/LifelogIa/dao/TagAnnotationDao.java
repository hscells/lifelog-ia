package hscells.LifelogIa.dao;

import hscells.LifelogIa.mapper.ImageMapper;
import hscells.LifelogIa.model.Image;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import java.sql.Array;
import java.util.List;

/**
 * Created by Harry Scells on 28/06/2016.
 */
public interface TagAnnotationDao {

    @SqlQuery("SELECT id, name, data FROM images WHERE id NOT IN (SELECT image_id FROM annotated_tag_images) ORDER BY random() LIMIT 10")
    @Mapper(ImageMapper.class)
    List<Image> getImages();

    @SqlQuery("SELECT value FROM tags")
    List<String> getTags();

    @SqlUpdate("INSERT INTO tags (value) VALUES (:tag)")
    void addTag(@Bind("tag") String tag);

    @SqlUpdate("INSERT INTO annotated_tag_images (image_id, person_id, annotation) VALUES (:image_id, :person_id, :annotation)")
    void annotateImage(@Bind("image_id") int imageId, @Bind("person_id") int personId, @Bind("annotation") Array annotation);

}
