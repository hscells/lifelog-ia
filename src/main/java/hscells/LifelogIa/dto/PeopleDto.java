package hscells.LifelogIa.dto;

/**
 * Created by Harry Scells on 19/12/2015.
 */
public class PeopleDto {

    private int id;
    private String name;

    public PeopleDto(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public PeopleDto(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
