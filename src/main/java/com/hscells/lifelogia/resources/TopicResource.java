package com.hscells.lifelogia.resources;

import com.hscells.lifelogia.model.Topic;
import com.hscells.lifelogia.service.TopicService;

import javax.annotation.security.PermitAll;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by Harry Scells on 28/06/2016.
 */
@PermitAll
@Path("/api/topics")
public class TopicResource {

    private TopicService topicService;

    public TopicResource(TopicService topicService) {
        this.topicService = topicService;
    }

    @GET
    @Path("/{imageId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Topic getTopic(@PathParam("imageId") String imageId) {
        return topicService.getTopic(imageId);
    }

}
