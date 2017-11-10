package hi.cord.com.user.main.price.service.history;

import hi.cord.com.user.common.service.abs.AbstractRestService;
import hi.cord.com.user.main.price.domain.history.PaidHistory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation=Propagation.REQUIRED, transactionManager="txManager", noRollbackFor={NullPointerException.class})
public interface PaidHistoryService extends AbstractRestService<PaidHistory> {

}