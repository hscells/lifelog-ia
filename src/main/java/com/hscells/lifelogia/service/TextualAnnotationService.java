package hscells.lifelogia.service;

import hscells.lifelogia.dao.TextualAnnotationDao;
import hscells.lifelogia.model.Image;

/**
 * Created by Harry Scells on 28/06/2016.
 */
public class TextualAnnotationService {

    private TextualAnnotationDao textualAnnotationDao;

    public TextualAnnotationService(TextualAnnotationDao textualAnnotationDao) {
        this.textualAnnotationDao = textualAnnotationDao;
    }

    public Image getImage() {
        return textualAnnotationDao.getImage();
    }

    public void annotate(int imageId, int personId, String annotation) {
        textualAnnotationDao.annotateImage(imageId, personId, annotation);
    }
}