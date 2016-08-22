package com.hscells.lifelogia.service;

import com.hscells.lifelogia.dao.TagAnnotationDao;
import com.hscells.lifelogia.model.Image;
import org.skife.jdbi.v2.DBI;

import java.sql.Array;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by Harry Scells on 28/06/2016.
 */
public class TagAnnotationService implements AutoCloseable {

    private TagAnnotationDao tagAnnotationDao;
    private Connection connection;

    public TagAnnotationService(TagAnnotationDao tagAnnotationDao, DBI jdbi) {
        this.tagAnnotationDao = tagAnnotationDao;
        this.connection = jdbi.open().getConnection();
    }

    public Image getImage() {
        Image image = tagAnnotationDao.getImage();
        image.setStartTime(new Timestamp(new Date().getTime()));
        return image;
    }

    public List<String> getTags() {
        return tagAnnotationDao.getTags();
    }

    public void addTags(List<String> tags) {
        List<String> dbTags = getTags();
        tags.stream().filter(tag -> !dbTags.contains(tag)).forEach(tag -> tagAnnotationDao.addTag(tag));
    }

    public void annotate(int imageId, int personId, List<String> annotation, Timestamp startTime) throws SQLException {
        addTags(annotation);
        Array tags = connection.createArrayOf("text", annotation.toArray());
        tagAnnotationDao.annotateImage(imageId, personId, tags, startTime, new Timestamp(new Date().getTime()));
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }
}
