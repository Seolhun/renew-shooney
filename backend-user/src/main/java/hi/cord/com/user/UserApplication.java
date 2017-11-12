package hi.cord.com.user;

import hi.cord.com.common.CommonApplication;
import hi.cord.com.user.config.YAMLConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

@SpringBootApplication
@EnableOAuth2Client
@EnableJpaRepositories(basePackages = "hi.cord.com.user")
@Import(CommonApplication.class)
public class UserApplication implements CommandLineRunner {
    private static final Logger LOG = LoggerFactory.getLogger(UserApplication.class);
    private YAMLConfig myConfig;

    @Autowired
    public UserApplication(YAMLConfig myConfig) {
        this.myConfig = myConfig;
    }


    @Override
    public void run(String... args) throws Exception {
        LOG.debug("p : messages : {}", myConfig.getMessage());
        LOG.debug("p : name : {}", myConfig.getName());
        LOG.debug("p : servers : {}", myConfig.getServers());
    }

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(UserApplication.class);
        app.run();
    }
}
