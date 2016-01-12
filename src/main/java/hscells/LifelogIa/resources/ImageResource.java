package hscells.LifelogIa.resources;

import hscells.LifelogIa.dao.ImageDao;
import hscells.LifelogIa.dao.PeopleDao;
import hscells.LifelogIa.dto.ImageDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Harry Scells on 16/12/2015.
 */

/**
 * ImageResource implements the endpoints that backbone.js expects
 */
@Path("/ws/images")
public class ImageResource {

    private static final Logger logger = LoggerFactory.getLogger(ImageResource.class);
    private final int chunkSize = 10;

    private ImageDao imageDao;
    private PeopleDao peopleDao;

    public ImageResource(ImageDao imageDao, PeopleDao peopleDao) {
        this.imageDao = imageDao;
        this.peopleDao = peopleDao;
    }

    public ImageResource(){}

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ImageDto> getImages() {
        int chunkId = imageDao.getChunk();
        long numAnnotated = 0;
        List<ImageDto> images = new ArrayList<>();
        do {
            images = imageDao.getChunkOfImages(chunkId);
            numAnnotated = images
                    .stream()
                    .filter(ImageDto::isAnnotated)
                    .count();
            if (numAnnotated == chunkSize) {
                imageDao.setChunkAnnotated(images.get(0).getChunk_id());
            }
            logger.info("Number of annotated images in this chunk: " + numAnnotated);
        } while (chunkSize == numAnnotated);
        return images;
    }

    @GET
    @Path("{imageId}")
    @Produces(MediaType.APPLICATION_JSON)
    public ImageDto getImage(@PathParam("imageId") String imageId){
        // No need to implement this yet
        return null;
    }

    @POST
    @Path("images")
    @Consumes(MediaType.APPLICATION_JSON)
    public void postImages(){
        // Don't think postImages will get used
    }

    @PUT
    @Path("{imageId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void putImage(@PathParam("imageId") int imageId, ImageDto imageDto) {
        imageDao.addAnnotatedImage(imageId, imageDto.getAnnotator(), imageDto.getAnnotation());
        imageDao.setImageAnnotated(imageId);
        List<ImageDto> images = imageDao.getChunkOfImages(imageDto.getChunk_id());
        int numAnnotated = 0;
        for (ImageDto image : images) {
            if (image.isAnnotated()) {
                numAnnotated++;
            }
        }
        if (chunkSize == numAnnotated) {
            imageDao.setChunkAnnotated(imageDto.getChunk_id());
        }
    }

    @DELETE
    @Path("{imageId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteImage(@PathParam("imageId") String imageId) {
        // Do nothing?
    }

}
