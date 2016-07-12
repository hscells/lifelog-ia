package hscells.LifelogIa.resources;

import hscells.LifelogIa.model.AssessmentAnnotation;
import hscells.LifelogIa.model.AssessmentImage;
import hscells.LifelogIa.model.Person;
import hscells.LifelogIa.service.AssessmentAnnotationService;
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
    public List<AssessmentImage> getImages() {
        return assessmentAnnotationService.getImages();
    }

    @POST
    @Path("/annotate")
    public Response annotate(@Auth Person person, AssessmentAnnotation assessmentAnnotation) {
        assessmentAnnotationService.annotate(new AssessmentAnnotation(assessmentAnnotation.getImageId(), person.getId(), assessmentAnnotation.getConceptId(), assessmentAnnotation.getRelevance()));
        return Response.status(200).build();
    }

}
