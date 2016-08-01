package hscells.LifelogIa.resources;

import hscells.LifelogIa.model.Image;
import hscells.LifelogIa.model.Person;
import hscells.LifelogIa.model.TextualAnnotation;
import hscells.LifelogIa.service.TextualAnnotationService;
import io.dropwizard.auth.Auth;

import javax.annotation.security.PermitAll;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Harry Scells on 28/06/2016.
 */
@PermitAll
@Path("/api/annotations/textual")
public class TextualAnnotationResource {

    private TextualAnnotationService textualAnnotationService;

    public TextualAnnotationResource(TextualAnnotationService textualAnnotationService) {
        this.textualAnnotationService = textualAnnotationService;
    }

    @GET
    @Path("/images")
    @Produces(MediaType.APPLICATION_JSON)
    public Image getImage() {
        return textualAnnotationService.getImage();
    }

    @POST
    @Path("/annotate")
    public Response annotate(@Auth Person person, TextualAnnotation textualAnnotation) {
        textualAnnotationService.annotate(textualAnnotation.getImageId(), person.getId(), textualAnnotation.getAnnotation());
        return Response.status(200).build();
    }

}
