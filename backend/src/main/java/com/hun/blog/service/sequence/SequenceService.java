package com.hun.blog.service.sequence;

import com.hun.blog.domain.sequence.CustomSequence;

public interface SequenceService {
    CustomSequence save(CustomSequence sequence);

    CustomSequence findByKey(String key);
}