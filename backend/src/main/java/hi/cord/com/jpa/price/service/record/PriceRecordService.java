package hi.cord.com.jpa.price.service.record;

import hi.cord.com.jpa.price.domain.PriceRecord;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(propagation=Propagation.REQUIRED, transactionManager="txManager", noRollbackFor={NullPointerException.class})
public interface PriceRecordService {

	PriceRecord findById(long id);

	void insert(PriceRecord priceRecords);

	PriceRecord update(PriceRecord priceRecord);

	void deleteById(long id);

	List<PriceRecord> findAllBy(PriceRecord priceRecord);
}