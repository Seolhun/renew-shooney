package com.hun.blog.controller.ask;

import com.hun.blog.domain.ask.AskData;
import com.hun.blog.domain.sequence.CustomSequences;
import com.hun.blog.service.ask.AskDataService;
import com.hun.blog.service.sequence.SequenceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/{version}/ask")
public class AskDataRestController {
    private static final Logger LOG = LoggerFactory.getLogger(AskDataRestController.class);
    private static final String SEQ_KEY = "ask";

    private AskDataService askDataService;
    private SequenceService sequenceService;

    @Autowired
    public AskDataRestController(AskDataService askDataService, SequenceService sequenceService) {
        this.askDataService = askDataService;
        this.sequenceService = sequenceService;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<AskData> postAsk(@PathVariable String version, @RequestBody AskData ask) {
        LOG.info("param : postAsk {}", version);

        CustomSequences sequence = sequenceService.findByKey(SEQ_KEY);
        ask.setId(sequence.getHospitalIdx()+"-"+sequence.getId());
        ask.setIndex(sequence.getId());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<Page<AskData>> getAskList(@PathVariable String version, AskData askData) {
        LOG.info("param : getAskList {}", version);

        Page<AskData> askDatas = askDataService.findByPage(askData, new PageRequest(0, 10));
        return new ResponseEntity<>(askDatas, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<AskData> getAsk(@PathVariable String version, @PathVariable String id) {
        AskData askData = askDataService.findOneById(id);
        return new ResponseEntity<>(askData, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<AskData> updateAsk(@PathVariable String version, @PathVariable String id) {
        LOG.info("where : saveNews");

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<AskData> deleteAsk(@PathVariable String version, @PathVariable String id) {
        AskData askData = askDataService.findOneById(id);
        return new ResponseEntity<>(askData, HttpStatus.OK);
    }
}
