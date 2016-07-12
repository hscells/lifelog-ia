package hscells.LifelogIa.model;

/**
 * Created by Harry Scells on 28/06/2016.
 */
public class TextualAnnotation {

    private int imageId;
    private int personId;
    private String annotation;

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
}
