package hi.cord.com.log.mongo.ask;

import hi.cord.com.log.mongo.ask.domain.AskData;
import hi.cord.com.log.mongo.ask.service.AskDataService;
import hi.cord.com.log.mongo.news.domain.NewsData;
import hi.cord.com.log.mongo.sequence.domain.CustomSequence;
import hi.cord.com.log.mongo.sequence.service.SequenceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
        LOG.debug("p  : postAsk {}", version);

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
        LOG.debug("p  : getAskList {}", version);

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
        LOG.debug("p  : getAsk {}", id);

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
