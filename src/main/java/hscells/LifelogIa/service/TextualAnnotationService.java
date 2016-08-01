package hscells.LifelogIa.service;

import hscells.LifelogIa.dao.TextualAnnotationDao;
import hscells.LifelogIa.model.Image;

import java.util.List;

/**
 * Created by Harry Scells on 28/06/2016.
 */
public class TextualAnnotationService {

    private TextualAnnotationDao textualAnnotationDao;

    public TextualAnnotationService(TextualAnnotationDao textualAnnotationDao) {
        this.textualAnnotationDao = textualAnnotationDao;
    }

    public List<Image> getImages() {
        return textualAnnotationDao.getImage();
    }

    public void annotate(int imageId, int personId, String annotation) {
        textualAnnotationDao.annotateImage(imageId, personId, annotation);
    }
}
