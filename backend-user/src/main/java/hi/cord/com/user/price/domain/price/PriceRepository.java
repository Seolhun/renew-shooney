package hi.cord.com.user.price.domain.price;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {

    List<Price> findAllBy(Price price);

    Price findById(long id);

    void deleteById(long id);

    long countBy(Price price);

    long countDistinctBy(Price price);
}

