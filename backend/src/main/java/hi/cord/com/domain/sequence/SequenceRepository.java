package hi.cord.com.domain.sequence;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SequenceRepository extends MongoRepository<CustomSequence, Long> {
    CustomSequence findById(long id);
}