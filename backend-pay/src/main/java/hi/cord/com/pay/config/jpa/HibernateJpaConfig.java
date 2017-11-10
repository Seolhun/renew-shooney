package hi.cord.com.pay.config.jpa;

import org.hibernate.SessionFactory;
import org.hibernate.jpa.HibernateEntityManagerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HibernateJpaConfig {
    @Bean(name = "sessionFactory")
    public SessionFactory sessionFactory(HibernateEntityManagerFactory hm) {
        return hm.getSessionFactory();
    }
}
