package hi.cord.com.pay.common.cron;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * The type Cron table.
 */
@Component
public class CronTable {
    private static final Logger LOG = LoggerFactory.getLogger(CronTable.class);
    /**
     * Gets news.
     */
    //매일 4시 30분 0초에 실행한다.
    @Scheduled(cron = "0 30 4 * * *")
    public void getNews() {

    }

    /**
     * Gets asks.
     */
//매일 5시 30분 0초에 실행한다.
    @Scheduled(cron = "0 30 5 * * *")
    public void getAsks() {
        // 실행될 로직

    }

//// 매월 1일 0시 0분 0초에 실행한다.
//    @Scheduled(cron = "0 0 0 1 * *")
//    public void anotherJob() {
//        // 실행될 로직
//    }
}

