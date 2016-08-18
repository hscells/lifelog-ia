package hscells.lifelogia.model;

/**
 * Created by Harry Scells on 1/08/2016.
 */
public class RelevanceConcept {

    private int id;
    private String value;

    public RelevanceConcept() {
    }

    public RelevanceConcept(int id, String value) {
        this.id = id;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public String getValue() {
        return value;
    }
}
