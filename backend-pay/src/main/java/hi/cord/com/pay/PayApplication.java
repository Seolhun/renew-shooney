package hi.cord.com.pay;

import hi.cord.com.pay.config.YAMLConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EnableOAuth2Sso
@EnableJpaRepositories(basePackages = "hi.cord.com.pay")
public class PayApplication implements CommandLineRunner {
    private static final Logger LOG = LoggerFactory.getLogger(PayApplication.class);
    private YAMLConfig myConfig;

    @Autowired
    public PayApplication(YAMLConfig myConfig) {
        this.myConfig = myConfig;
    }

    @Override
    public void run(String... args) throws Exception {
        LOG.debug("p : messages : {}", myConfig.getMessage());
        LOG.debug("p : name : {}", myConfig.getName());
        LOG.debug("p : servers : {}", myConfig.getServers());
    }

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(PayApplication.class);
        app.run();
    }
}
