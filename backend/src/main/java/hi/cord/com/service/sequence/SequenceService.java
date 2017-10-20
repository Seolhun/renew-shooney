package hi.cord.com.service.sequence;

import hi.cord.com.domain.sequence.CustomSequence;

public interface SequenceService {
    CustomSequence save(CustomSequence sequence);

    CustomSequence findByKey(String key);
}