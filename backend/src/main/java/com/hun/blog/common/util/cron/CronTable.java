package com.hun.blog.common.util.cron;

import com.hun.blog.domain.sequence.CustomSequence;
import com.hun.blog.service.ask.AskDataService;
import com.hun.blog.service.news.NewsDataService;
import com.hun.blog.service.sequence.SequenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * The type Cron table.
 */
@Component
public class CronTable {

    private NewsDataService newsDataService;
    private AskDataService askDataService;
    private SequenceService sequenceService;

    /**
     * Instantiates a new Cron table.
     *
     * @param newsDataService the news data service
     * @param askDataService  the ask data service
     * @param sequenceService the sequence service
     */
    @Autowired
    public CronTable(NewsDataService newsDataService, AskDataService askDataService, SequenceService sequenceService) {
        this.newsDataService = newsDataService;
        this.askDataService = askDataService;
        this.sequenceService = sequenceService;
    }

    /**
     * Gets news.
     */
//매일 5시 30분 0초에 실행한다.
    @Scheduled(cron = "0 30 4 * * *")
    public void getNews() {
        CustomSequence customSequences = sequenceService.findByKey("news");
        newsDataService.getNewsThread(customSequences.getId()).start();
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

