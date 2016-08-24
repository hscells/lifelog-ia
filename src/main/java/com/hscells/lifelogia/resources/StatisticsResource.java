package com.hscells.lifelogia.resources;

import com.hscells.lifelogia.model.Person;
import com.hscells.lifelogia.model.stats.Statistic;
import com.hscells.lifelogia.service.stats.StatisticsService;
import com.hscells.lifelogia.views.StatisticsView;
import io.dropwizard.auth.Auth;
import io.dropwizard.views.View;

import javax.annotation.security.PermitAll;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by Harry Scells on 24/08/2016.
 */
@Path("/stats")
public class StatisticsResource {

    private StatisticsService statisticsService;

    public StatisticsResource(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GET
    @PermitAll
    @Produces(MediaType.TEXT_HTML)
    public View statsView(@Auth Person person) {
        Statistic statistic = statisticsService.getStatisticForPerson(person.getId());
        return new StatisticsView(statistic);
    }
}
