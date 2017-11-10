package hi.cord.com.user.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"hi.cord.com.user.common.domain.entity.user"})
public class CommonApplication {
    private static final Logger LOG = LoggerFactory.getLogger(CommonApplication.class);

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(CommonApplication.class);
        app.run();
    }
}
