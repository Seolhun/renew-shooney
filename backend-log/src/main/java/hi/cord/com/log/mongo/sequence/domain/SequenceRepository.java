package hi.cord.com.log.mongo.sequence.domain;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SequenceRepository extends MongoRepository<CustomSequence, Long> {
    CustomSequence findById(long id);
}