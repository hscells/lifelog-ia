package hscells.LifelogIa.resources;

import hscells.LifelogIa.dao.TagAnnotationDao;
import hscells.LifelogIa.model.Image;
import hscells.LifelogIa.model.Person;
import hscells.LifelogIa.model.TagAnnotation;
import hscells.LifelogIa.model.TextualAnnotation;
import hscells.LifelogIa.service.TagAnnotationService;
import hscells.LifelogIa.service.TextualAnnotationService;
import io.dropwizard.auth.Auth;

import javax.annotation.security.PermitAll;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Harry Scells on 28/06/2016.
 */
@PermitAll
@Path("/api/annotations/tag")
public class TagAnnotationResource {

    private TagAnnotationService tagAnnotationService;

    public TagAnnotationResource(TagAnnotationService tagAnnotationService) {
        this.tagAnnotationService = tagAnnotationService;
    }

    @GET
    @Path("images")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Image> getImages() {
        return tagAnnotationService.getImages();
    }

    @GET
    @Path("tags")
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> getTags() {
        return tagAnnotationService.getTags();
    }

    @POST
    @Path("annotate")
    public Response annotate(@Auth Person person, TagAnnotation tagAnnotation) throws SQLException {
        tagAnnotationService.annotate(tagAnnotation.getImageId(), person.getId(), tagAnnotation.getAnnotation());
        return Response.status(200).build();
    }

}
