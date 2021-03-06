package hi.cord.com.pay.main.domain.history;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaidHistoryRepository extends JpaRepository<PaidHistory, Long> {

    List<PaidHistory> findAllBy(PaidHistory paidHistory);

    PaidHistory findById(long id);

    void deleteById(long id);

    long countBy(PaidHistory paidHistory);

    long countDistinctBy(PaidHistory paidHistory);
}