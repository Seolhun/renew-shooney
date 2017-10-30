
package hi.cord.com.config.database;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "shunEntityManagerFactory",
        transactionManagerRef = "shunTransactionManager",
        basePackages = {"hi.cord.com.jpa2"})
public class SecondDataBaseConfig {

    @Bean(name = "shunDataSource")
    @ConfigurationProperties(prefix = "shun.datasource")
    public DataSource barDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "shunEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean barEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("shunDataSource") DataSource barDataSource) {
        return builder
                .dataSource(barDataSource)
                .packages("hi.cord.com.jpa2")
                .persistenceUnit("bar")
                .build();
    }

    @Bean(name = "shunTransactionManager")
    public PlatformTransactionManager barTransactionManager(
            @Qualifier("shunEntityManagerFactory") EntityManagerFactory barEntityManagerFactory) {
        return new JpaTransactionManager(barEntityManagerFactory);
    }

}