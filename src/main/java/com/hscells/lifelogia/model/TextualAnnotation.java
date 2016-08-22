package com.hscells.lifelogia.model;

import java.sql.Timestamp;

/**
 * Created by Harry Scells on 28/06/2016.
 */
public class TextualAnnotation {

    private int imageId;
    private int personId;
    private String annotation;
    private Timestamp startTime;

    public TextualAnnotation() {
    }

    public TextualAnnotation(int id, int imageId, int personId, String annotation) {
        this.imageId = imageId;
        this.personId = personId;
        this.annotation = annotation;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }
}
