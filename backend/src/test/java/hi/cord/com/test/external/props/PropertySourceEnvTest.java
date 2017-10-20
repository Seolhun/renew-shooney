package hi.cord.com.test.external.props;

import hi.cord.com.test.jms.FakeJmsBroker;
import hi.cord.test.config.external.props.ExternalPropsEnvironment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

/**
 * The type Property source env test.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ExternalPropsEnvironment.class)
public class PropertySourceEnvTest {

    @Autowired
    FakeJmsBroker fakeJmsBroker;

    @Test
    public void testPropsSet() throws Exception {
        assertEquals("10.10.10.123", fakeJmsBroker.getUrl());
        assertEquals(3330, fakeJmsBroker.getPort().intValue());
        assertEquals("Ron", fakeJmsBroker.getUser());
        assertEquals("Burgundy", fakeJmsBroker.getPassword());
    }

}
