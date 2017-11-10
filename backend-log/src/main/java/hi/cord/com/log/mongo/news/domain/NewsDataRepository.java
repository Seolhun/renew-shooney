package hi.cord.com.log.mongo.news.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsDataRepository extends MongoRepository<NewsData, String> {
    NewsData findById(String pk);

    NewsData findByIdx(long idx);

    long count();

    Page<NewsData> findAll(Pageable pageable);
}
