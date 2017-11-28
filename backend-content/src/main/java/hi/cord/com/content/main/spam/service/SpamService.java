package hi.cord.com.content.main.spam.service;

import hi.cord.com.common.domain.pagination.Pagination;
import hi.cord.com.common.service.rest.CommonRestService;
import hi.cord.com.content.main.spam.domain.Spam;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRED, transactionManager = "shunTransactionManager", noRollbackFor = {NullPointerException.class})
public interface SpamService extends CommonRestService<Spam> {

    Pagination<Spam> findAll(Spam spam, Pageable pageable);
}