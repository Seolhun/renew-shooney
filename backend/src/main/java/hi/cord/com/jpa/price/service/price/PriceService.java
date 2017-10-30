package hi.cord.com.jpa.price.service.price;

import hi.cord.com.jpa.price.domain.Price;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(propagation=Propagation.REQUIRED, transactionManager="txManager", noRollbackFor={NullPointerException.class})
public interface PriceService {

	Price findById(long id);

	Price insert(Price price);

	Price update(Price price);

	void deleteById(long id);

	List<Price> findAllBy(Price price);
}