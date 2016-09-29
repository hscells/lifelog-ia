package com.hscells.lifelogia.resources;

import com.hscells.lifelogia.dto.PersonDto;
import com.hscells.lifelogia.service.PersonService;
import com.hscells.lifelogia.service.RecoveryService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.concurrent.ExecutionException;

/**
 * Created by Harry Scells on 18/12/2015.
 */
@Path("/api/people")
public class PersonResource {

    private PersonService personService;
    private RecoveryService recoveryService;

    public PersonResource(PersonService personService, RecoveryService recoveryService) {
        this.personService = personService;
        this.recoveryService = recoveryService;
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

    @POST
    @Path("/recover/{code}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response recover(@PathParam("code") String code, PersonDto details) {
        if (recoveryService.resetPassword(details, code)) {
            return Response.status(200).build();
        }
        return Response.status(404).build();
    }

    @GET
    @Path("/reset/{email}")
    public Response reset(@PathParam("email") String email) throws ExecutionException {
        recoveryService.sendResetEmail(email);
        return Response.status(200).build();
    }

}
