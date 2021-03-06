package hi.cord.com.log.mongo.ask.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AskDataRepository extends MongoRepository<AskData, String> {

    AskData findById(String pk);

    AskData findByIdx(long idx);

	long count();

    Page<AskData> findAll(Pageable pageable);
}
