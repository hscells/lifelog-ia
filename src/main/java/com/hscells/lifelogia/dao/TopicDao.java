package com.hscells.lifelogia.dao;

import com.hscells.lifelogia.mapper.TopicMapper;
import com.hscells.lifelogia.model.Topic;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

/**
 * Created by Harry Scells on 28/06/2016.
 */
public interface TopicDao {

    @SqlQuery("SELECT topic_id, title, description, narrative FROM topics WHERE topic_id = (SELECT topic_id FROM images_topics WHERE image_id = :imageId);")
    @Mapper(TopicMapper.class)
    Topic getTopic(@Bind("imageId") String imageId);

}
