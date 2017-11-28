package hi.cord.com.content.config.multitask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.annotation.PostConstruct;

/**
 * @author HunSeol
 * @created_date 2017. 11. 1.
 * @IDE IntelliJ IDEA
 */

@Configuration
public class ThreadPoolConfig {
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Value("${shun.thread.timeout}")
    private int threadTimeout;

    @Value("${shun.thread.core-size}")
    private int corePoolSize;

    @Value("${shun.thread.max-pool}")
    private int maxPoolSize;

    @Value("${shun.thread.queue-capacity}")
    private int queueCapacity;

    @PostConstruct
    public void init(){
        LOG.info("r : threadTimeout {}", threadTimeout);
        LOG.info("r : corePoolSize {}", corePoolSize);
        LOG.info("r : maxPoolSize {}", maxPoolSize);
        LOG.info("r : queueCapacity {}", queueCapacity);
    }

    /**
     * Thread pool task executor thread pool task executor.
     *
     * @return the thread pool task executor
     *
     * @Process
     * 1. Check application.yml
     * 2. @{@link org.springframework.scheduling.annotation.Async }("taskExecutor1")
     * 3. Attatch Main Boot application, @{@link org.springframework.scheduling.annotation.EnableAsync }
     */
// Need Test
    @Bean("taskExecutor1")
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setKeepAliveSeconds(threadTimeout);
        taskExecutor.setCorePoolSize(corePoolSize);
        taskExecutor.setMaxPoolSize(maxPoolSize);
        taskExecutor.setQueueCapacity(queueCapacity);
        return taskExecutor;
    }
}
