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
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.scheduling.concurrent.ScheduledExecutorFactoryBean;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

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

    //Cron Factory bean
    @Bean
    public ScheduledExecutorFactoryBean schedulerExecutor() {
        ScheduledExecutorFactoryBean scheduledExecutorFactoryBean = new ScheduledExecutorFactoryBean();
        scheduledExecutorFactoryBean.setPoolSize(10);
        return scheduledExecutorFactoryBean;
    }

    // Need Test
    @Bean("threadPoolExecutor")
    public ThreadPoolExecutor threadPoolExecutor() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(500, 1000, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(), new ThreadPoolExecutor.DiscardOldestPolicy());
        return threadPoolExecutor;
    }
}
