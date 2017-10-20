package hi.cord.com;

import hi.cord.com.config.YAMLConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.scheduling.concurrent.ScheduledExecutorFactoryBean;

@SpringBootApplication
//@EnableOAuth2Sso
public class BlogApplication implements CommandLineRunner {
    private static final Logger LOG = LoggerFactory.getLogger(BlogApplication.class);

    @Autowired
    private YAMLConfig myConfig;

    @Override
    public void run(String... args) throws Exception {
        LOG.info("param : environment : {}", myConfig.getEnvironment());
        LOG.info("param : name : {}", myConfig.getName());
        LOG.info("param : servers : {}", myConfig.getServers());
    }

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(BlogApplication.class);
        app.run();
    }

    //MongoDB Template
    @Bean
    public MongoTemplate mongoTemplate(MongoDbFactory mongoDbFactory, MongoMappingContext context) {
        MappingMongoConverter converter = new MappingMongoConverter(new DefaultDbRefResolver(mongoDbFactory), context);
        converter.setTypeMapper(new DefaultMongoTypeMapper(null));
        return new MongoTemplate(mongoDbFactory, converter);
    }

    //Cron Factory bean
    @Bean
    public ScheduledExecutorFactoryBean schedulerExecutor() {
        ScheduledExecutorFactoryBean scheduledExecutorFactoryBean = new ScheduledExecutorFactoryBean();
        scheduledExecutorFactoryBean.setPoolSize(10);
        return scheduledExecutorFactoryBean;
    }
}
