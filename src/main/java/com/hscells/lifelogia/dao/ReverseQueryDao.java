package com.hscells.lifelogia.dao;

import com.hscells.lifelogia.mapper.ImageMapper;
import com.hscells.lifelogia.model.Image;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import java.sql.Timestamp;

/**
 * Created by Harry Scells on 28/06/2016.
 */
public interface ReverseQueryDao {

    @SqlQuery("SELECT id, name, data FROM images WHERE id IN (SELECT image_id FROM (SELECT i, image_id FROM (SELECT 1 i, image_id FROM annotated_text_images UNION (SELECT 0, id from images ORDER BY random())) as x WHERE image_id NOT IN (SELECT image_id FROM annotated_query_images) ORDER BY i DESC LIMIT 1) a) LIMIT 1")
    @Mapper(ImageMapper.class)
    Image getImage();

    @SqlUpdate("INSERT INTO annotated_query_images (image_id, person_id, annotation, start_time, end_time) VALUES (:image_id, :person_id, :annotation, :startTime, :endTime)")
    void annotateImage(@Bind("image_id") int imageId, @Bind("person_id") int personId, @Bind("annotation") String annotation, @Bind("startTime")Timestamp startTime, @Bind("endTime") Timestamp endTime);

}
