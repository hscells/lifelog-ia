package com.hscells.lifelogia.service;

import com.hscells.lifelogia.dao.TopicDao;
import com.hscells.lifelogia.model.Topic;

/**
 * Created by Harry Scells on 28/06/2016.
 */
public class TopicService {

    private TopicDao topicDao;

    public TopicService(TopicDao topicDao) {
        this.topicDao = topicDao;
    }

    public Topic getTopic(String imageId) {
        return topicDao.getTopic(imageId);
    }

}
