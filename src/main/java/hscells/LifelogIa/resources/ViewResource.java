package hscells.LifelogIa.resources;

import hscells.LifelogIa.views.AnnotationTagView;
import hscells.LifelogIa.views.AnnotationTextualView;
import hscells.LifelogIa.views.HomeView;
import hscells.LifelogIa.views.RegisterView;
import io.dropwizard.views.View;

import javax.annotation.security.PermitAll;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by Harry Scells on 18/12/2015.
 */
@Path("/")
public class ViewResource {

    @GET
    @Produces(MediaType.TEXT_HTML)
    public View loginView(){
        return new HomeView();
    }

    @GET
    @Path("register")
    @Produces(MediaType.TEXT_HTML)
    public View registerView() {
        return new RegisterView();
    }

    @GET
    @PermitAll
    @Path("annotation/textual")
    @Produces(MediaType.TEXT_HTML)
    public View annotationTextualView() {
        return new AnnotationTextualView();
    }

    @GET
    @PermitAll
    @Path("annotation/tag")
    @Produces(MediaType.TEXT_HTML)
    public View annotationTagView() {
        return new AnnotationTagView();
    }

}
