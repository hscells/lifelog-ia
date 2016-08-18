package hscells.lifelogia.service;

import hscells.lifelogia.dao.ReverseQueryDao;
import hscells.lifelogia.model.Image;

/**
 * Created by Harry Scells on 28/06/2016.
 */
public class ReverseQueryAnnotationService {

    private ReverseQueryDao reverseQueryDao;

    public ReverseQueryAnnotationService(ReverseQueryDao textualAnnotationDao) {
        this.reverseQueryDao = textualAnnotationDao;
    }

    public Image getImage() {
        return reverseQueryDao.getImage();
    }

    public void annotate(int imageId, int personId, String annotation) {
        reverseQueryDao.annotateImage(imageId, personId, annotation);
    }
}