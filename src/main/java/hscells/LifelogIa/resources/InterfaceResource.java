package hscells.LifelogIa.resources;

import hscells.LifelogIa.views.ImageListView;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by Harry Scells on 18/12/2015.
 */
@Path("/")
public class InterfaceResource {

    public InterfaceResource(){}

    @GET
    @Produces({MediaType.TEXT_HTML})
    public Object fetch(){
        return new ImageListView();
    }


}
