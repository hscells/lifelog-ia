package com.hscells.lifelogia.resources;

import com.hscells.lifelogia.service.TextualAnnotationService;
import com.hscells.lifelogia.model.Image;
import com.hscells.lifelogia.model.Person;
import com.hscells.lifelogia.model.TextualAnnotation;
import io.dropwizard.auth.Auth;

import javax.annotation.security.PermitAll;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Timestamp;
import java.util.Date;

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
        textualAnnotationService.annotate(textualAnnotation.getImageId(), person.getId(), textualAnnotation.getAnnotation(), textualAnnotation.getStartTime());
        return Response.status(200).build();
    }

}
