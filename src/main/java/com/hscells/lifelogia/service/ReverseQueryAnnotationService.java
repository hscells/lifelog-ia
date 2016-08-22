package com.hscells.lifelogia.service;

import com.hscells.lifelogia.dao.ReverseQueryDao;
import com.hscells.lifelogia.model.Image;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by Harry Scells on 28/06/2016.
 */
public class ReverseQueryAnnotationService {

    private ReverseQueryDao reverseQueryDao;

    public ReverseQueryAnnotationService(ReverseQueryDao textualAnnotationDao) {
        this.reverseQueryDao = textualAnnotationDao;
    }

    public Image getImage() {
        Image image = reverseQueryDao.getImage();
        image.setStartTime(new Timestamp(new Date().getTime()));
        return image;
    }

    public void annotate(int imageId, int personId, String annotation, Timestamp startTime) {
        reverseQueryDao.annotateImage(imageId, personId, annotation, startTime, new Timestamp(new Date().getTime()));
    }
}
