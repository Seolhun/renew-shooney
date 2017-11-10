package hi.cord.com.log.config.mongodb;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "hi.cord.com.log.mongo")
@ConfigurationProperties
public class MongoConfig extends AbstractMongoConfiguration {

    @Autowired
    private Environment environment;

    @Override
    public Mongo mongo() throws Exception {
        return new MongoClient(environment.getRequiredProperty("spring.data.mongodb.host"), Integer.parseInt(environment.getRequiredProperty("mongodb.config.port")));
    }

    @Override
    protected String getDatabaseName() {
        return environment.getRequiredProperty("spring.data.mongodb.database");
    }

    @Bean
    public MongoClient mongoClient() throws Exception {
//        MongoCredential credential = MongoCredential.createCredential(environment.getRequiredProperty("spring.data.mongodb.username"), "admin", environment.getRequiredProperty("spring.data.mongodb.password").toCharArray());
//        ServerAddress serverAddress = new ServerAddress(environment.getRequiredProperty("spring.data.mongodb.host"), Integer.parseInt(environment.getRequiredProperty("spring.data.mongodb.port")));
//        MongoClient mongoClient = new MongoClient(serverAddress, Arrays.asList(credential));
        MongoClient mongoClient = new MongoClient();
        return mongoClient;
    }

    @Bean
    public MongoDbFactory mongoDbFactory() throws Exception {
        SimpleMongoDbFactory simpleMongoDbFactory = new SimpleMongoDbFactory(mongoClient(), environment.getRequiredProperty("spring.data.mongodb.database"));
        return simpleMongoDbFactory;
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
        return mongoTemplate;
    }
}