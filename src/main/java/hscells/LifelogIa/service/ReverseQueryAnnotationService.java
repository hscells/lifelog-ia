package hscells.LifelogIa.service;

import hscells.LifelogIa.dao.ReverseQueryDao;
import hscells.LifelogIa.dao.TextualAnnotationDao;
import hscells.LifelogIa.model.Image;

import java.util.List;

/**
 * Created by Harry Scells on 28/06/2016.
 */
public class ReverseQueryAnnotationService {

    private ReverseQueryDao reverseQueryDao;

    public ReverseQueryAnnotationService(ReverseQueryDao textualAnnotationDao) {
        this.reverseQueryDao = textualAnnotationDao;
    }

    public List<Image> getImages() {
        return reverseQueryDao.getImages();
    }

    public void annotate(int imageId, int personId, String annotation) {
        reverseQueryDao.annotateImage(imageId, personId, annotation);
    }
}
