package com.hscells.lifelogia.resources;

import com.hscells.lifelogia.dto.PersonDto;
import com.hscells.lifelogia.service.PersonService;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Harry Scells on 18/12/2015.
 */
@Path("/api/people")
public class PersonResource {

    private PersonService personService;

    public PersonResource(PersonService personService) {
        this.personService = personService;
    }

    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registerPerson(PersonDto details) {
        if (personService.registerPerson(details.getAlias(), details.getEmail(), details.getPassword())) {
            return Response.status(200).build();
        }
        return Response.status(404).build();
    }

}
