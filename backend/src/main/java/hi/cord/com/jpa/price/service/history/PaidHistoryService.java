package hi.cord.com.jpa.price.service.history;

import hi.cord.com.common.service.rest.AbstractRestService;
import hi.cord.com.jpa.price.domain.history.PaidHistory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation=Propagation.REQUIRED, transactionManager="txManager", noRollbackFor={NullPointerException.class})
public interface PaidHistoryService extends AbstractRestService<PaidHistory> {

}