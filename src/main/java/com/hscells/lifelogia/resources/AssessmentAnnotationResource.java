package com.hscells.lifelogia.resources;

import com.hscells.lifelogia.model.AssessmentAnnotation;
import com.hscells.lifelogia.model.Image;
import com.hscells.lifelogia.model.RelevanceConcept;
import com.hscells.lifelogia.service.AssessmentAnnotationService;
import com.hscells.lifelogia.model.Person;
import io.dropwizard.auth.Auth;

import javax.annotation.security.PermitAll;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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
        return assessmentAnnotationService.getImage();
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
        assessmentAnnotationService.annotate(new AssessmentAnnotation(assessmentAnnotation.getImageId(), person.getId(), assessmentAnnotation.getConceptId(), assessmentAnnotation.getRelevance()));
        return Response.status(200).build();
    }

}
