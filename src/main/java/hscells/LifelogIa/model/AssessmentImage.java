package hscells.LifelogIa.model;

/**
 * Created by Harry Scells on 2/07/2016.
 */
public class AssessmentImage extends Image {

    private String concept;
    private int conceptId;

    public AssessmentImage(int id, String name, String data, String concept, int conceptId) {
        super(id, name, data);
        this.concept = concept;
        this.conceptId = conceptId;
    }

    public String getConcept() {
        return concept;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }

    public int getConceptId() {
        return conceptId;
    }

    public void setConceptId(int conceptId) {
        this.conceptId = conceptId;
    }
}
