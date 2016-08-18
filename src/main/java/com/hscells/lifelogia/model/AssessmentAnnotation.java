package hscells.lifelogia.model;

/**
 * Created by Harry Scells on 1/08/2016.
 */
public class AssessmentAnnotation {

    private int imageId;
    private int personId;
    private int conceptId;
    private int relevance;

    public AssessmentAnnotation() {
    }

    public AssessmentAnnotation(int imageId, int personId, int conceptId, int relevance) {
        this.imageId = imageId;
        this.personId = personId;
        this.conceptId = conceptId;
        this.relevance = relevance;
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

    public int getConceptId() {
        return conceptId;
    }

    public void setConceptId(int conceptId) {
        this.conceptId = conceptId;
    }

    public int getRelevance() {
        return relevance;
    }

    public void setRelevance(int relevance) {
        this.relevance = relevance;
    }
}
