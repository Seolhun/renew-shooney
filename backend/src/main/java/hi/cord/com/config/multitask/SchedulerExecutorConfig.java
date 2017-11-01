package hi.cord.com.config.multitask;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ScheduledExecutorFactoryBean;

/**
 * @author HunSeol
 * @created_date 2017. 11. 1.
 * @IDE IntelliJ IDEA
 */

@Configuration
public class SchedulerExecutorConfig {


    /**
     * Scheduler executor scheduled executor factory bean.
     *
     * @return the scheduled executor factory bean
     *
     * @Process
     * 1. Check application.yml
     * 2. Attach this Annotation to run with thread in RestController
     * - @{@link org.springframework.scheduling.annotation.Async }("taskExecutor1")
     * 3. Attach this Annotation into Boot application,
     * - @{@link org.springframework.scheduling.annotation.EnableAsync }
     */
//Cron Factory bean
    @Bean("schedulerExecutor1")
    public ScheduledExecutorFactoryBean schedulerExecutor() {
        ScheduledExecutorFactoryBean schedulerExecutor = new ScheduledExecutorFactoryBean();
        schedulerExecutor.setPoolSize(10);
        schedulerExecutor.setThreadNamePrefix("shun-");
        return schedulerExecutor;
    }
}
