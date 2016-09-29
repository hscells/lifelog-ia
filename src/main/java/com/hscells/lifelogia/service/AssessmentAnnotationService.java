package com.hscells.lifelogia.service;

import com.hscells.lifelogia.dao.AssessmentAnnotationDao;
import com.hscells.lifelogia.model.AssessmentAnnotation;
import com.hscells.lifelogia.model.Image;
import com.hscells.lifelogia.model.RelevanceConcept;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by Harry Scells on 2/07/2016.
 */
public class AssessmentAnnotationService {

    private AssessmentAnnotationDao assessmentAnnotationDao;

    public AssessmentAnnotationService(AssessmentAnnotationDao assessmentAnnotationDao) {
        this.assessmentAnnotationDao = assessmentAnnotationDao;
    }

    public Image getImage() {
        return assessmentAnnotationDao.getImage();
    }

    public List<RelevanceConcept> getConceptsForImage(int imageId) {
        return assessmentAnnotationDao.getConceptsForImage(imageId);
    }

    public void annotate(AssessmentAnnotation assessmentAnnotation) {
        assessmentAnnotationDao.annotate(assessmentAnnotation.getImageId(), assessmentAnnotation.getPersonId(), assessmentAnnotation.getConceptId(), assessmentAnnotation.getRelevance(), assessmentAnnotation.getStartTime(), new Timestamp(new Date().getTime()));
    }
}
