package com.hscells.lifelogia.model.stats;

import java.util.List;

/**
 * Created by Harry Scells on 24/08/2016.
 */
public class Statistic {

    private String personName;
    private List<AnnotationStatistics> statistics;

    public Statistic() {
    }

    public Statistic(String personName, List<AnnotationStatistics> statistics) {
        this.personName = personName;
        this.statistics = statistics;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public List<AnnotationStatistics> getStatistics() {
        return statistics;
    }

    public void setStatistics(List<AnnotationStatistics> statistics) {
        this.statistics = statistics;
    }
}
