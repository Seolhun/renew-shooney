package hi.cord.com.jpa.price.repository;

import hi.cord.com.jpa.price.domain.Price;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PriceRepository extends JpaRepository<Price, Long> {

    Price findById(Long id);

    void insert(Price price);

    void deleteById(Long id);

    List<Price> findAllBy(Price price);

}

