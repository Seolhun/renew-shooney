package hi.cord.com.pay.main.service.history;

import hi.cord.com.pay.main.domain.history.PaidHistory;
import hi.cord.com.common.service.rest.CommonRestService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation=Propagation.REQUIRED, transactionManager="transactionManager", noRollbackFor={NullPointerException.class})
public interface PaidHistoryService extends CommonRestService<PaidHistory> {

}