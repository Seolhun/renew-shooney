package hi.cord.com.domain.news;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@EnableScan
public interface NewsDataRepository extends CrudRepository<NewsData, String> {
	NewsData findByPk(String pk);

    NewsData findByIdx(long idx);

	long count();

    Page<NewsData> findAllBy(Pageable pageable);
}
