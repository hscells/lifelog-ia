package hscells.lifelogia.model;

/**
 * Created by Harry Scells on 11/01/2016.
 */
public class Leaderboard {

    private String name;
    private int numAnnotated;

    public Leaderboard(String name, int numAnnotated) {
        this.name = name;
        this.numAnnotated = numAnnotated;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumAnnotated() {
        return numAnnotated;
    }

    public void setNumAnnotated(int numAnnotated) {
        this.numAnnotated = numAnnotated;
    }

}
