package hi.cord.com.jpa.price.domain.history;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaidHistoryRepository extends JpaRepository<PaidHistory, Long> {

    List<PaidHistory> findAllBy(PaidHistory paidHistory);

    PaidHistory findById(long id);

    PaidHistory insert(PaidHistory paidHistory);

    void delete(long id);

    long countBy(PaidHistory paidHistory);

    long countDistinctBy(PaidHistory paidHistory);
}