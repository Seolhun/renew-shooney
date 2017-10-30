package hi.cord.com.mongo.controller.news;

import hi.cord.com.common.domain.Pagination;
import hi.cord.com.mongo.domain.news.NewsData;
import hi.cord.com.mongo.domain.sequence.CustomSequence;
import hi.cord.com.mongo.service.news.NewsDataService;
import hi.cord.com.mongo.service.sequence.SequenceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * The type News data rest controller.
 * author : HunSeol
 */
@RestController
@RequestMapping(value = "/api/{version}/news")
public class NewsDataRestController {
    private static final Logger LOG = LoggerFactory.getLogger(NewsDataRestController.class);
    private static final String SEQ_KEY = "news";

    private NewsDataService newsDataService;
    private SequenceService sequenceService;

    /**
     * Instantiates a new News data rest controller.
     *
     * @param newsDataService the news data service
     * @param sequenceService the sequence service
     */
    @Autowired
    public NewsDataRestController(NewsDataService newsDataService, SequenceService sequenceService) {
        this.newsDataService = newsDataService;
        this.sequenceService = sequenceService;
    }

    /**
     * Save news response entity.
     *
     * @param version the version
     * @return the response entity
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<NewsData> saveNews(@PathVariable String version, NewsData newsData) {
        LOG.info("param : saveNews {}", newsData.toString());

        CustomSequence customSequence = sequenceService.findByKey("news");
        newsDataService.getNewsThread(customSequence.getId()).start();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Save news response entity.
     *
     * @param version the version
     * @param idx     the idx
     * @return the response entity
     */
    @RequestMapping(value = "{idx}", method = RequestMethod.POST)
    public ResponseEntity<NewsData> saveNewsByIdx(@PathVariable String version, @PathVariable long idx) {
        LOG.info("where : saveNews");
        newsDataService.getNewsThread(idx).start();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Gets news list data.
     *
     * @param pagination the pagination
     * @return the news list data
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<Page<NewsData>> getNewsListData(Pagination<NewsData> pagination) {
        // 전체 게시판 갯수 확인
        PageRequest pageRequest = new PageRequest(pagination.getPageIndex(), pagination.getLimit(), Direction.DESC, "idx");
        Page<NewsData> newsDatas = newsDataService.findByPage(pagination.getE(), pageRequest);
        return new ResponseEntity<>(newsDatas, HttpStatus.OK);
    }

    /**
     * Gets news detail.
     *
     * @param version the version
     * @param id      the id
     * @return the news detail
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public NewsData getNewsDetail(@PathVariable String version, @PathVariable String id) {
        return newsDataService.findById(id);
    }

    /**
     * Stop thread news response entity.
     *
     * @return the response entity
     */
    @RequestMapping(value = "/stop", method = RequestMethod.GET)
    public ResponseEntity<NewsData> stopThreadNews() {
        LOG.info("where : stopThreadNews");
        stopNewsThread();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void stopNewsThread() {
        // 현재 돌고있는 쓰레드를 객체로 반환.
        Thread thread = Thread.currentThread();
        String name = thread.getName();
        System.out.println("현재 쓰레드 이름 : " + name);
        thread.run();
    }
}
