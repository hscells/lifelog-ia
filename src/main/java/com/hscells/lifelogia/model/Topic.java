package com.hscells.lifelogia.model;

/**
 * Created by Harry Scells on 29/09/2016.
 */
public class Topic {

    private String topicId;
    private String title;
    private String description;
    private String narrative;

    public Topic(String topicId, String title, String description, String narrative) {
        this.topicId = topicId;
        this.title = title;
        this.description = description;
        this.narrative = narrative;
    }

    public String getTopicId() {
        return topicId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getNarrative() {
        return narrative;
    }
}
