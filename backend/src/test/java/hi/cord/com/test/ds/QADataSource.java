package hi.cord.com.test.ds;

import hi.cord.test.ds.FakeDataSource;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * The type Qa data source.
 */
@Component
@Profile("qa")
public class QADataSource implements FakeDataSource {

    @Override
    public String getConnectionInfo() {
        return "I'm the QA Datasource";
    }
}
