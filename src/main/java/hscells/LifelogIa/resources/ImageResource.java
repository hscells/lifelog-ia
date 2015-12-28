package hscells.LifelogIa.resources;

import hscells.LifelogIa.dao.ImageDao;
import hscells.LifelogIa.dao.PeopleDao;
import hscells.LifelogIa.dto.ImageDto;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by Harry Scells on 16/12/2015.
 */

/**
 * ImageResource implements the endpoints that backbone.js expects
 */
@Path("/ws/images")
public class ImageResource {

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
        List<ImageDto> images = imageDao.getChunkOfImages(chunkId);
//        List<ImageDto> images = new ArrayList<ImageDto>();
//        images.add(new ImageDto(0, "offline-example-1",
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
        int chunkSize = 10;
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
