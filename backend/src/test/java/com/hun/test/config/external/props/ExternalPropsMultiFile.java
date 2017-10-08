package com.hun.test.config.external.props;

import com.hun.blog.test.jms.FakeJmsBroker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * Created by jt on 5/7/16.
 */
@Configuration
@PropertySource({"classpath:application.properties", "classpath:encrypted-testing.properties"})
public class ExternalPropsMultiFile {

    @Autowired
    Environment env;

    @Bean
    public FakeJmsBroker fakeJmsBrokerMulti(){
        FakeJmsBroker fakeJmsBroker = new FakeJmsBroker();
        fakeJmsBroker.setUrl(env.getProperty("hun.jms.server"));
        fakeJmsBroker.setPort(env.getRequiredProperty("hun.jms.port", Integer.class));
        fakeJmsBroker.setUser(env.getProperty("hun.jms.user"));
        fakeJmsBroker.setPassword(env.getProperty("hun.jms.encrypted.password"));
        return fakeJmsBroker;
    }
}
