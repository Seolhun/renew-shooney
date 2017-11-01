package hi.cord.com;

import hi.cord.com.config.YAMLConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.concurrent.ScheduledExecutorFactoryBean;

@SpringBootApplication
//@EnableOAuth2Sso
@EnableJpaRepositories(basePackages = "hi.cord.com.jpa")
public class BlogApplication implements CommandLineRunner {
    private static final Logger LOG = LoggerFactory.getLogger(BlogApplication.class);
    private YAMLConfig myConfig;

    @Autowired
    public BlogApplication(YAMLConfig myConfig) {
        this.myConfig = myConfig;
    }

    @Override
    public void run(String... args) throws Exception {
        LOG.info("param : messages : {}", myConfig.getMessage());
        LOG.info("param : name : {}", myConfig.getName());
        LOG.info("param : servers : {}", myConfig.getServers());
    }

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(BlogApplication.class);
        app.run();
    }
}
