package hi.cord.com.pay.main.service.price;

import hi.cord.com.pay.main.domain.price.Price;
import hi.cord.com.common.service.abs.AbstractRestService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation=Propagation.REQUIRED, transactionManager="txManager", noRollbackFor={NullPointerException.class})
public interface PriceService extends AbstractRestService<Price> {

}