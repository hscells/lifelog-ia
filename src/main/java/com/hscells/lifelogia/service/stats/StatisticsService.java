package com.hscells.lifelogia.service.stats;

import com.hscells.lifelogia.dao.stats.StatisticsDao;
import com.hscells.lifelogia.model.stats.AnnotationStatistics;
import com.hscells.lifelogia.model.stats.Statistic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Harry Scells on 24/08/2016.
 */
public class StatisticsService {

    private StatisticsDao statisticsDao;

    public StatisticsService(StatisticsDao statisticsDao) {
        this.statisticsDao = statisticsDao;
    }

    public Statistic getStatisticForPerson(int personId) {
        // get all the statistics for each type of annotation
        AnnotationStatistics textStatistics = statisticsDao.getTextStatisticsForPerson(personId);
        textStatistics.setName("Textual Annotations");

        AnnotationStatistics tagStatistics = statisticsDao.getTagStatisticsForPerson(personId);
        tagStatistics.setName("Tag Annotations");

        AnnotationStatistics queryStatistics = statisticsDao.getQueryStatisticsForPerson(personId);
        queryStatistics.setName("Query Annotations");

        AnnotationStatistics assessmentStatistics = statisticsDao.getAssessmentStatisticsForPerson(personId);
        assessmentStatistics.setName("Relevance Assessment Annotations");

        // add them all to a big old list
        List<AnnotationStatistics> annotationStatistics = new ArrayList<>();
        annotationStatistics.add(textStatistics);
        annotationStatistics.add(tagStatistics);
        annotationStatistics.add(queryStatistics);
        annotationStatistics.add(assessmentStatistics);

        // get the persons alias to display it later
        String personName = statisticsDao.getPersonName(personId);

        // badabing badaboom fugazi fugazi don't worry about it
        return new Statistic(personName, annotationStatistics);
    }
}
