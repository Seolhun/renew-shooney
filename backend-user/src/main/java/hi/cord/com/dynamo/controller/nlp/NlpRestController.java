package hi.cord.com.dynamo.controller.nlp;

import hi.cord.com.dynamo.service.nlp.NlpPhraseService;
import hi.cord.com.mongo.domain.news.NewsData;
import hi.cord.com.mongo.domain.sequence.CustomSequence;
import hi.cord.com.mongo.service.sequence.SequenceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type News data rest controller.
 * author : HunSeol
 */
@RestController
@RequestMapping(value = "/api/{version}/nlp")
public class NlpRestController {
    private static final Logger LOG = LoggerFactory.getLogger(hi.cord.com.mongo.controller.news.NewsDataRestController.class);
    private static final String SEQ_KEY = "news";

    private NlpPhraseService nlpPhraseService;
    private SequenceService sequenceService;

    /**
     * Instantiates a new News data rest controller.
     *
     * @param nlpPhraseService the nlp data service
     * @param sequenceService  the sequence service
     */
    @Autowired
    public NlpRestController(NlpPhraseService nlpPhraseService, SequenceService sequenceService) {
        this.nlpPhraseService = nlpPhraseService;
        this.sequenceService = sequenceService;
    }

    /**
     * Save news response entity.
     *
     * @param version the version
     * @return the response entity
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<NewsData> saveNlp(@PathVariable String version) {
        CustomSequence customSequence = sequenceService.findByKey("news");
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
