package hscells.lifelogia.model;

import java.util.List;

/**
 * Created by Harry Scells on 28/06/2016.
 */
public class TagAnnotation {

    private int imageId;
    private int personId;
    private List<String> annotation;

    public TagAnnotation() {
    }

    public TagAnnotation(int id, int imageId, int personId, List<String> annotation) {
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

    public List<String> getAnnotation() {
        return annotation;
    }

    public void setAnnotation(List<String> annotation) {
        this.annotation = annotation;
    }
}
