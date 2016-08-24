package com.hscells.lifelogia.model.stats;

/**
 * Created by Harry Scells on 24/08/2016.
 */
public class AnnotationStatistics {

    private String name;
    private String numAnnotated;
    private String totalTime;
    private String averageTime;

    public AnnotationStatistics() {
    }

    public AnnotationStatistics(String name, String numAnnotated, String totalTime, String averageTime) {
        this.name = name;
        this.numAnnotated = numAnnotated;
        this.totalTime = totalTime;
        this.averageTime = averageTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumAnnotated() {
        return numAnnotated;
    }

    public void setNumAnnotated(String numAnnotated) {
        this.numAnnotated = numAnnotated;
    }

    public String getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(String totalTime) {
        this.totalTime = totalTime;
    }

    public String getAverageTime() {
        return averageTime;
    }

    public void setAverageTime(String averageTime) {
        this.averageTime = averageTime;
    }
}
