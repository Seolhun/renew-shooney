package hi.cord.com;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import hi.cord.com.config.YAMLConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

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
        LOG.debug("p : messages : {}", myConfig.getMessage());
        LOG.debug("p : name : {}", myConfig.getName());
        LOG.debug("p : servers : {}", myConfig.getServers());
    }

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(BlogApplication.class);
        app.run();
    }
}
