package hi.cord.test.config.external.props;

import hi.cord.com.test.jms.FakeJmsBroker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * The type External props environment.
 */
@Configuration
@PropertySource("classpath:application.properties")
public class ExternalPropsEnvironment {

    @Autowired
    Environment env; //also from Spring 3.1

    @Bean
    public FakeJmsBroker fakeJmsBrokerEnv(){
        FakeJmsBroker fakeJmsBroker = new FakeJmsBroker();
        fakeJmsBroker.setUrl(env.getProperty("hun.jms.server"));
        fakeJmsBroker.setPort(env.getRequiredProperty("hun.jms.port", Integer.class));
        fakeJmsBroker.setUser(env.getProperty("hun.jms.user"));
        fakeJmsBroker.setPassword(env.getProperty("hun.jms.password"));
        return fakeJmsBroker;
    }
}
