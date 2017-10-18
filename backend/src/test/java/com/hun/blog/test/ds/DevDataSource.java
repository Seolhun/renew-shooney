package com.hun.blog.test.ds;

import com.hun.test.ds.FakeDataSource;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * The type Dev data source.
 */
@Component
@Profile("dev")
public class DevDataSource implements FakeDataSource {
    @Override
    public String getConnectionInfo() {
        return "I'm the Development DataSource";
    }
}
