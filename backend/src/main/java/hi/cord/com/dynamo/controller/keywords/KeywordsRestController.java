package hi.cord.com.dynamo.controller.keywords;

import hi.cord.com.dynamo.domain.keywords.Keywords;
import hi.cord.com.dynamo.service.keywords.KeywordsDynamoService;
import hi.cord.com.mongo.service.sequence.SequenceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * The type Ask data rest controller.
 */
@RestController
@RequestMapping(value = "/api/{version}/keywords")
public class KeywordsRestController {
    private static final Logger LOG = LoggerFactory.getLogger(KeywordsRestController.class);
    private static final String SEQ_KEY = "keywords";

    private KeywordsDynamoService keywordsService;
    private SequenceService sequenceService;

    /**
     * Instantiates a new Keywords rest controller.
     *
     * @param keywordsService the ask data service
     * @param sequenceService the sequence service
     */
    @Autowired
    public KeywordsRestController(KeywordsDynamoService keywordsService, SequenceService sequenceService) {
        this.keywordsService = keywordsService;
        this.sequenceService = sequenceService;
    }

    /**
     * Post Keywords response entity.
     *
     * @param version the version
     * @return the response entity
     */
    @PostMapping(value = "")
    public ResponseEntity<Keywords> postKeywords(@PathVariable String version, @RequestBody Keywords keywords) {
        LOG.debug("p : postKeywords {}", keywords.toString());
        keywordsService.saveKeywords(keywords);
        return new ResponseEntity<>(keywords, HttpStatus.OK);
    }

    /**
     * Gets Keywords page.
     *
     * @param version the version
     * @param keyword the ask data
     * @return the ask list
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<Page<Keywords>> getKeywordsList(@PathVariable String version, Keywords keyword) {
        LOG.debug("p : getKeywordsById {}", keyword.toString());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Gets keywords by id.
     *
     * @param version the version
     * @param id      the id
     * @return the ask
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Keywords> getKeywordsById(@PathVariable String version, @PathVariable String id) {
        LOG.debug("p : getKeywordsById {}", id);
        Keywords keywords = keywordsService.findById(id);
        return new ResponseEntity<>(keywords, HttpStatus.OK);
    }

    /**
     * Update keywords by id response entity.
     *
     * @param version the version
     * @param id      the id
     * @return the response entity
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Keywords> updateKeywordsById(@PathVariable String version, @PathVariable String id) {
        LOG.debug("p : updateKeywordsById {}", id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Delete keywords by id response entity.
     *
     * @param version the version
     * @param id      the id
     * @return the response entity
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Keywords> deleteKeywordsById(@PathVariable String version, @PathVariable String id) {
        LOG.debug("p : deleteKeywordsById {}", id);
        Keywords keywords = keywordsService.findById(id);
        return new ResponseEntity<>(keywords, HttpStatus.OK);
    }
}
