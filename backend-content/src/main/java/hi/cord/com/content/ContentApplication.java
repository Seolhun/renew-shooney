package hi.cord.com.content;

import hi.cord.com.common.CommonApplication;
import hi.cord.com.content.config.YAMLConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(CommonApplication.class)
public class ContentApplication implements CommandLineRunner {
    private static final Logger LOG = LoggerFactory.getLogger(ContentApplication.class);
    private YAMLConfig myConfig;

    @Autowired
    public ContentApplication(YAMLConfig myConfig) {
        this.myConfig = myConfig;
    }

    @Override
    public void run(String... args) throws Exception {
        LOG.debug("p : messages : {}", myConfig.getMessage());
        LOG.debug("p : name : {}", myConfig.getName());
        LOG.debug("p : servers : {}", myConfig.getServers());
    }

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(ContentApplication.class);
        app.run();
    }
}
