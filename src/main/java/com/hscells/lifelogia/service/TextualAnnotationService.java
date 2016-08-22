package com.hscells.lifelogia.service;

import com.hscells.lifelogia.dao.TextualAnnotationDao;
import com.hscells.lifelogia.model.Image;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by Harry Scells on 28/06/2016.
 */
public class TextualAnnotationService {

    private TextualAnnotationDao textualAnnotationDao;

    public TextualAnnotationService(TextualAnnotationDao textualAnnotationDao) {
        this.textualAnnotationDao = textualAnnotationDao;
    }

    public Image getImage() {
        Image image = textualAnnotationDao.getImage();
        image.setStartTime(new Timestamp(new Date().getTime()));
        return image;
    }

    public void annotate(int imageId, int personId, String annotation, Timestamp startTime) {
        textualAnnotationDao.annotateImage(imageId, personId, annotation, startTime, new Timestamp(new Date().getTime()));
    }
}
