package hi.cord.com.jpa2.spam.domain.log;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpamLogRepository extends JpaRepository<SpamLog, Long> {
    List<SpamLog> findAllBy(SpamLog spamLog);

    SpamLog findById(long id);

    boolean deleteById(long id);

    long countBy(SpamLog spamLog);

    long countDistinctBy(SpamLog spamLog);
}
