package com.hun.blog.test.config;

import com.hun.blog.test.jms.FakeJmsBroker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The type Spring boot java config.
 */
@Configuration
public class SpringBootJavaConfig {
    @Value("${hun.jms.server}")
    String jmsServer;

    @Value("${hun.jms.port}")
    Integer jmsPort;

    @Value("${hun.jms.user}")
    String jmsUser;

    @Value("${hun.jms.password}")
    String jmsPassword;

    @Bean
    public FakeJmsBroker fakeJmsBroker() {
        FakeJmsBroker fakeJmsBroker = new FakeJmsBroker();
        fakeJmsBroker.setUrl(jmsServer);
        fakeJmsBroker.setPort(jmsPort);
        fakeJmsBroker.setUser(jmsUser);
        fakeJmsBroker.setPassword(jmsPassword);
        return fakeJmsBroker;
    }
}
