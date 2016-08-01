package hscells.LifelogIa.service;

import hscells.LifelogIa.dao.ReverseQueryDao;
import hscells.LifelogIa.model.Image;

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
