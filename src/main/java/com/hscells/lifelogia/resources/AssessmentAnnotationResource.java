package com.hscells.lifelogia.resources;

import com.hscells.lifelogia.model.AssessmentAnnotation;
import com.hscells.lifelogia.model.Image;
import com.hscells.lifelogia.model.Person;
import com.hscells.lifelogia.model.RelevanceConcept;
import com.hscells.lifelogia.service.AssessmentAnnotationService;
import io.dropwizard.auth.Auth;

import javax.annotation.security.PermitAll;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by Harry Scells on 2/07/2016.
 */
@PermitAll
@Path("/api/annotations/assessment")
public class AssessmentAnnotationResource {

    private AssessmentAnnotationService assessmentAnnotationService;

    public AssessmentAnnotationResource(AssessmentAnnotationService assessmentAnnotationService) {
        this.assessmentAnnotationService = assessmentAnnotationService;
    }

    @GET
    @Path("/images")
    @Produces(MediaType.APPLICATION_JSON)
    public Image getImage() {
        Image image = assessmentAnnotationService.getImage();
        image.setStartTime(new Timestamp(new Date().getTime()));
        return image;
    }

    @GET
    @Path("/concepts/{imageId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<RelevanceConcept> getConceptsForImage(@PathParam("imageId") int imageId) {
        return assessmentAnnotationService.getConceptsForImage(imageId);
    }

    @POST
    @Path("/annotate")
    public Response annotate(@Auth Person person, AssessmentAnnotation assessmentAnnotation) {
        assessmentAnnotation.setPersonId(person.getId());
        assessmentAnnotationService.annotate(assessmentAnnotation);
        return Response.status(200).build();
    }

}
