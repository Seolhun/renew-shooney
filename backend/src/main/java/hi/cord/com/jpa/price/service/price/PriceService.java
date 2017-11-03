package hi.cord.com.jpa.price.service.price;

import hi.cord.com.common.service.abs.AbstractRestService;
import hi.cord.com.jpa.price.domain.price.Price;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation=Propagation.REQUIRED, transactionManager="txManager", noRollbackFor={NullPointerException.class})
public interface PriceService extends AbstractRestService<Price> {

}