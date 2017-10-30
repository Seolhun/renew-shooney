package hi.cord.com.jpa.price.repository;

import hi.cord.com.jpa.price.domain.PriceRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PriceRecordRepository extends JpaRepository<PriceRecord, Long> {

    PriceRecord findById(long id);

    void insert(PriceRecord priceRecord);

    void deleteById(long id);

    List<PriceRecord> findAllBy(PriceRecord priceRecord);
}