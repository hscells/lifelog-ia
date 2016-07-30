package hscells.LifelogIa.resources;

import hscells.LifelogIa.model.Image;
import hscells.LifelogIa.model.Person;
import hscells.LifelogIa.model.TextualAnnotation;
import hscells.LifelogIa.service.ReverseQueryAnnotationService;
import io.dropwizard.auth.Auth;

import javax.annotation.security.PermitAll;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by Harry Scells on 28/06/2016.
 */
@PermitAll
@Path("/api/annotations/query")
public class ReverseQueryAnnotationResource {

    private ReverseQueryAnnotationService reverseQueryAnnotationService;

    public ReverseQueryAnnotationResource(ReverseQueryAnnotationService reverseQueryAnnotationService) {
        this.reverseQueryAnnotationService = reverseQueryAnnotationService;
    }

    @GET
    @Path("/images")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Image> getImages() {
        return reverseQueryAnnotationService.getImages();
    }

    @POST
    @Path("/annotate")
    public Response annotate(@Auth Person person, TextualAnnotation textualAnnotation) {
        reverseQueryAnnotationService.annotate(textualAnnotation.getImageId(), person.getId(), textualAnnotation.getAnnotation());
        return Response.status(200).build();
    }

}
