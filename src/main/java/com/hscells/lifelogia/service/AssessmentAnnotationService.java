package hscells.lifelogia.service;

import hscells.lifelogia.dao.AssessmentAnnotationDao;
import hscells.lifelogia.model.AssessmentAnnotation;
import hscells.lifelogia.model.Image;
import hscells.lifelogia.model.RelevanceConcept;

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
        assessmentAnnotationDao.annotate(assessmentAnnotation.getImageId(), assessmentAnnotation.getPersonId(), assessmentAnnotation.getConceptId(), assessmentAnnotation.getRelevance());
    }
}
