package hi.cord.com.pay.main.service.price;

import hi.cord.com.pay.main.domain.price.Price;
import hi.cord.com.common.service.rest.CommonRestService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation=Propagation.REQUIRED, transactionManager="transactionManager", noRollbackFor={NullPointerException.class})
public interface PriceService extends CommonRestService<Price> {

}