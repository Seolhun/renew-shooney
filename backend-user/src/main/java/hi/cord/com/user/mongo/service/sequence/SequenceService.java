package hi.cord.com.user.mongo.service.sequence;

import hi.cord.com.user.mongo.domain.sequence.CustomSequence;

public interface SequenceService {
    CustomSequence save(CustomSequence sequence);

    CustomSequence findByKey(String key);
}