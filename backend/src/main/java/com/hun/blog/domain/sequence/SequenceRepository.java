package com.hun.blog.domain.sequence;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SequenceRepository extends MongoRepository<CustomSequences, Long> {
    CustomSequences findById(long id);
}