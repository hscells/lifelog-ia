package hscells.LifelogIa.service;

import hscells.LifelogIa.dao.TagAnnotationDao;
import hscells.LifelogIa.model.Image;
import org.skife.jdbi.v2.DBI;

import java.sql.Array;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Harry Scells on 28/06/2016.
 */
public class TagAnnotationService {

    private TagAnnotationDao tagAnnotationDao;
    private DBI jdbi;

    public TagAnnotationService(TagAnnotationDao tagAnnotationDao, DBI jdbi) {
        this.tagAnnotationDao = tagAnnotationDao;
        this.jdbi = jdbi;
    }

    public List<Image> getImages() {
        return tagAnnotationDao.getImages();
    }

    public List<String> getTags() {
        return tagAnnotationDao.getTags();
    }

    public void addTags(List<String> tags) {
        List<String> dbTags = getTags();
        tags.stream().filter(tag -> !dbTags.contains(tag)).forEach(tag -> tagAnnotationDao.addTag(tag));
    }

    public void annotate(int imageId, int personId, List<String> annotation) throws SQLException {
        addTags(annotation);
        Array tags = jdbi.open().getConnection().createArrayOf("text", annotation.toArray());
        tagAnnotationDao.annotateImage(imageId, personId, tags);
    }
}
