package hi.cord.com.log.mongo.sequence.service;

import hi.cord.com.log.mongo.sequence.domain.CustomSequence;

public interface SequenceService {
    CustomSequence save(CustomSequence sequence);

    CustomSequence findByKey(String key);
}