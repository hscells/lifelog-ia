package hscells.LifelogIa.resources;

import hscells.LifelogIa.dao.StatsDao;
import hscells.LifelogIa.views.ImageListView;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Map;

/**
 * Created by Harry Scells on 18/12/2015.
 */
@Path("/")
public class InterfaceResource {

    private StatsDao statsDao;

    public InterfaceResource(StatsDao statsDao) {
        this.statsDao = statsDao;
    }

    @GET
    @Produces({MediaType.TEXT_HTML})
    public Object fetch(){
        return new ImageListView(statsDao.getLeaderboardScores(), statsDao.getUnannotatedImageCount());
    }

}
