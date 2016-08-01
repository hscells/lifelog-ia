package hscells.LifelogIa.service;

import hscells.LifelogIa.dao.AssessmentAnnotationDao;
import hscells.LifelogIa.model.AssessmentAnnotation;
import hscells.LifelogIa.model.Image;
import hscells.LifelogIa.model.RelevanceConcept;

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
