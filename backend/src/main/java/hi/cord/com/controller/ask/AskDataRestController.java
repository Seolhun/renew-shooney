package hi.cord.com.controller.ask;

import hi.cord.com.domain.ask.AskData;
import hi.cord.com.domain.news.NewsData;
import hi.cord.com.domain.sequence.CustomSequence;
import hi.cord.com.service.ask.AskDataService;
import hi.cord.com.service.sequence.SequenceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * The type Ask data rest controller.
 */
@RestController
@RequestMapping(value = "/api/{version}/ask")
public class AskDataRestController {
    private static final Logger LOG = LoggerFactory.getLogger(AskDataRestController.class);
    private static final String SEQ_KEY = "ask";

    private AskDataService askDataService;
    private SequenceService sequenceService;

    /**
     * Instantiates a new Ask data rest controller.
     *
     * @param askDataService  the ask data service
     * @param sequenceService the sequence service
     */
    @Autowired
    public AskDataRestController(AskDataService askDataService, SequenceService sequenceService) {
        this.askDataService = askDataService;
        this.sequenceService = sequenceService;
    }

    /**
     * Post ask response entity.
     *
     * @param version the version
     * @return the response entity
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<AskData> postAsk(@PathVariable String version) {
        LOG.info("param : postAsk {}", version);

        CustomSequence sequence = sequenceService.findByKey(SEQ_KEY);
        askDataService.getAskThread(sequence.getId()).start();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Gets ask list.
     *
     * @param version the version
     * @param askData the ask data
     * @return the ask list
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<Page<AskData>> getAskList(@PathVariable String version, AskData askData) {
        LOG.info("param : getAskList {}", version);

        Page<AskData> askDatas = askDataService.findByPage(askData, new PageRequest(0, 10));
        return new ResponseEntity<>(askDatas, HttpStatus.OK);
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
        askDataService.getAskThread(idx).start();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Gets ask.
     *
     * @param version the version
     * @param id      the id
     * @return the ask
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<AskData> getAsk(@PathVariable String version, @PathVariable String id) {
        LOG.info("param : getAsk {}", id);

        AskData askData = askDataService.findById(id);
        return new ResponseEntity<>(askData, HttpStatus.OK);
    }

    /**
     * Update ask response entity.
     *
     * @param version the version
     * @param id      the id
     * @return the response entity
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<AskData> updateAsk(@PathVariable String version, @PathVariable String id) {
        LOG.info("where : saveNews");

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Delete ask response entity.
     *
     * @param version the version
     * @param id      the id
     * @return the response entity
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<AskData> deleteAsk(@PathVariable String version, @PathVariable String id) {
        AskData askData = askDataService.findById(id);
        return new ResponseEntity<>(askData, HttpStatus.OK);
    }
}
