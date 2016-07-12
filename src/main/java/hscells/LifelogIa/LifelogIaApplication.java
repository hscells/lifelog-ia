package hscells.LifelogIa;

import com.github.dirkraft.dropwizard.fileassets.FileAssetsBundle;
import hscells.LifelogIa.auth.PersonAuthenticator;
import hscells.LifelogIa.auth.PersonAuthoriser;
import hscells.LifelogIa.dao.*;
import hscells.LifelogIa.model.Person;
import hscells.LifelogIa.resources.*;
import hscells.LifelogIa.service.AssessmentAnnotationService;
import hscells.LifelogIa.service.PersonService;
import hscells.LifelogIa.service.TagAnnotationService;
import hscells.LifelogIa.service.TextualAnnotationService;
import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.CachingAuthenticator;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.auth.basic.BasicCredentials;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.skife.jdbi.v2.DBI;

/**
 * Created by Harry Scells on 16/12/2015.
 */
public class LifelogIaApplication extends Application<LifelogIaConfiguration> {

    @Override
    public void run(LifelogIaConfiguration configuration, Environment environment){

        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment, configuration.getDataSourceFactory(), "postgresql");

        final TextualAnnotationDao textualAnnotationDao = jdbi.onDemand(TextualAnnotationDao.class);
        final TagAnnotationDao tagAnnotationDao = jdbi.onDemand(TagAnnotationDao.class);
        final AssessmentAnnotationDao assessmentAnnotationDao = jdbi.onDemand(AssessmentAnnotationDao.class);
        final PersonDao personDao = jdbi.onDemand(PersonDao.class);
        final StatsDao statsDao = jdbi.onDemand(StatsDao.class);

        final PersonService personService = new PersonService(personDao);
        final TextualAnnotationService textualAnnotationService = new TextualAnnotationService(textualAnnotationDao);
        final TagAnnotationService tagAnnotationService = new TagAnnotationService(tagAnnotationDao, jdbi);
        final AssessmentAnnotationService assessmentAnnotationService = new AssessmentAnnotationService(assessmentAnnotationDao);

        PersonAuthenticator personAuthenticator = new PersonAuthenticator(personService);

        environment.jersey().register(new TextualAnnotationResource(textualAnnotationService));
        environment.jersey().register(new TagAnnotationResource(tagAnnotationService));
        environment.jersey().register(new AssessmentAnnotationResource(assessmentAnnotationService));
        environment.jersey().register(new PersonResource(personService));
        environment.jersey().register(new ViewResource());

        CachingAuthenticator<BasicCredentials, Person> cachingAuthenticator = new CachingAuthenticator<>(
                environment.metrics(), personAuthenticator, configuration.getAuthenticationCachePolicy());

        // authentication stuff
        environment.jersey().register(new AuthDynamicFeature(
                new BasicCredentialAuthFilter.Builder<Person>()
                .setAuthenticator(cachingAuthenticator)
                .setAuthorizer(new PersonAuthoriser())
                .setRealm("LIFELOG-IMAGE-INTERFACE-HEROKU")
                .buildAuthFilter()
        ));
        environment.jersey().register(RolesAllowedDynamicFeature.class);
        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(Person.class));

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
//        bootstrap.addBundle(new AssetsBundle("/static", "/static", "*"));
        bootstrap.addBundle(new FileAssetsBundle("src/main/resources/static", "/static", "*"));
        bootstrap.addBundle(new ViewBundle<>());
    }

}
