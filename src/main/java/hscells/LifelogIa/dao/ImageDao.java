package hscells.LifelogIa.dao;

import hscells.LifelogIa.dto.ImageDto;
import hscells.LifelogIa.dto.ImageDtoMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import java.util.List;

/**
 * Created by Harry Scells on 17/12/2015.
 */
public interface ImageDao {

    /**
     * Get a list of unannotated images from a chunk which has not had all its images annotated
     * @param id
     * @return
     */
    @SqlQuery("SELECT id, chunk_id, name, data, annotated FROM images WHERE chunk_id = :chunk_id AND annotated = false")
    @Mapper(ImageDtoMapper.class)
    List<ImageDto> getChunkOfImages(@Bind("chunk_id") int id);

    /**
     * Pick a random chunk to be annotated
     * @return
     */
    @SqlQuery("SELECT id FROM chunks WHERE annotated = false ORDER BY random() LIMIT 1")
    int getChunk();

    /**
     * Sets an image to be annotated
     * @param imageId
     */
    @SqlUpdate("UPDATE images SET annotated = true WHERE id = :imageId")
    void setImageAnnotated(@Bind("imageId") int imageId);

    /**
     * Sets a chunk to be annotated
     * @param chunkId
     */
    @SqlUpdate("UPDATE chunks SET annotated = true WHERE id = :chunkId")
    void setChunkAnnotated(@Bind("chunkId") int chunkId);


    /**
     * Add an annotated image to the database
     * @param imageId
     * @param personId
     * @param annotation
     */
    @SqlUpdate("INSERT INTO annotated_images (image_id, person_id, annotation) VALUES (:imageId, :personId, :annotation)")
    void addAnnotatedImage(@Bind("imageId") int imageId, @Bind("personId") int personId, @Bind("annotation") String annotation);

}

