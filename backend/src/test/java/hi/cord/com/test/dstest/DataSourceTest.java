package hi.cord.com.test.dstest;

/**
 * Created by jt on 5/21/16.
 */

import hi.cord.com.test.config.DataSourceConfig;
import hi.cord.test.ds.FakeDataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes= {DataSourceConfig.class})
@ActiveProfiles("dev")
public class DataSourceTest {

    private FakeDataSource fakeDataSource;

    @Autowired
    public void setFakeDataSource(FakeDataSource fakeDataSource) {
        this.fakeDataSource = fakeDataSource;
    }

    @Test
    public void TestDataSource() throws Exception {
        System.out.println(fakeDataSource.getConnectionInfo());

    }
}
