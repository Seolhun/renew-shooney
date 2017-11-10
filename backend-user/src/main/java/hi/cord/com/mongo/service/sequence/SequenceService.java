package hi.cord.com.mongo.service.sequence;

import hi.cord.com.mongo.domain.sequence.CustomSequence;

public interface SequenceService {
    CustomSequence save(CustomSequence sequence);

    CustomSequence findByKey(String key);
}