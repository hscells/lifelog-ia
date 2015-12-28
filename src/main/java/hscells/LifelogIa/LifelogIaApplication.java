package hscells.LifelogIa;

import hscells.LifelogIa.dao.ImageDao;
import hscells.LifelogIa.dao.PeopleDao;
import hscells.LifelogIa.resources.ImageResource;
import hscells.LifelogIa.resources.InterfaceResource;
import hscells.LifelogIa.resources.PeopleResource;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;
import org.skife.jdbi.v2.DBI;

/**
 * Created by Harry Scells on 16/12/2015.
 */
public class LifelogIaApplication extends Application<LifelogIaConfiguration> {

    @Override
    public void run(LifelogIaConfiguration configuration, Environment environment){

        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment, configuration.getDataSourceFactory(), "postgresql");

        final ImageDao imageDao = jdbi.onDemand(ImageDao.class);
        final PeopleDao peopleDao = jdbi.onDemand(PeopleDao.class);

        environment.jersey().register(new ImageResource(imageDao, peopleDao));
        environment.jersey().register(new PeopleResource(peopleDao));
        environment.jersey().register(new InterfaceResource());
//        environment.jersey().register(new ImageResource());
    }

    public static void main(String[] args) throws Exception {
        new LifelogIaApplication().run(args);
    }

    @Override
    public String getName() {
        return "lifelog-ia";
    }

    @Override
    public void initialize(Bootstrap<LifelogIaConfiguration> bootstrap) {
        bootstrap.addBundle(new AssetsBundle("/static", "/static", "*"));
        bootstrap.addBundle(new ViewBundle());
    }

}
