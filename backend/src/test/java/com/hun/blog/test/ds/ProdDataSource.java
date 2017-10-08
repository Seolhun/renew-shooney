package com.hun.blog.test.ds;

import com.hun.test.ds.FakeDataSource;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * The type Prod data source.
 */
@Component
@Profile("prod")
public class ProdDataSource implements FakeDataSource {
    @Override
    public String getConnectionInfo() {
        return "I'm the Production Datasource";
    }
}
