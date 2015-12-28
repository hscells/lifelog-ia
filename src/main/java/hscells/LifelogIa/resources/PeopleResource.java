package hscells.LifelogIa.resources;

import hscells.LifelogIa.dao.PeopleDao;
import hscells.LifelogIa.dto.PeopleDto;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by Harry Scells on 18/12/2015.
 */
@Path("/ws/people")
public class PeopleResource {

    private PeopleDao peopleDao;

    public PeopleResource(PeopleDao peopleDao) {
        this.peopleDao = peopleDao;
    }

    @GET
    @Path("{personId}")
    public boolean personExists(@PathParam("personId") String personId) {
            if(peopleDao.getPersonIdByName(personId) == 0) {
            return false;
        }
        return true;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public PeopleDto addPerson(PeopleDto person) {
        if (!personExists(person.getName())) {
            peopleDao.addPerson(person.getName());
        }
        return new PeopleDto(peopleDao.getPersonIdByName(person.getName()), person.getName());
    }

}
