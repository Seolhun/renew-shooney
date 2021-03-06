package hi.cord.com.user.config.jpa;//
//package hi.cord.com.content.config.database.mariadb;
//
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.persistence.EntityManagerFactory;
//import javax.sql.DataSource;
//
//@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories(
//        entityManagerFactoryRef = "shunEntityManagerFactory",
//        transactionManagerRef = "shunTransactionManager",
//        basePackages = {"hi.cord.com.user.main"})
//public class SecondDataBaseConfig {
//
//    @Bean(name = "shunDataSource")
//    @ConfigurationProperties(prefix = "shun.datasource")
//    public DataSource shunDataSource() {
//        return DataSourceBuilder.create().build();
//    }
//
//    @Bean(name = "shunEntityManagerFactory")
//    public LocalContainerEntityManagerFactoryBean shunEntityManagerFactory(
//            EntityManagerFactoryBuilder builder,
//            @Qualifier("shunDataSource") DataSource shunDataSource) {
//        return builder
//                .dataSource(shunDataSource)
//                .packages("hi.cord.com.user.main")
//                .persistenceUnit("user")
//                .build();
//    }
//
//    @Bean(name = "shunTransactionManager")
//    public PlatformTransactionManager shunTransactionManager(
//            @Qualifier("shunEntityManagerFactory") EntityManagerFactory shunEntityManagerFactory) {
//        return new JpaTransactionManager(shunEntityManagerFactory);
//    }
//}