package hi.cord.com.content.main2.spam.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpamRepository extends JpaRepository<Spam, Long> {
    List<Spam> findAllBy(Spam spam);

    Spam findById(long id);

    boolean deleteById(long id);

    long countBy(Spam spam);

    long countDistinctBy(Spam spam);
}
