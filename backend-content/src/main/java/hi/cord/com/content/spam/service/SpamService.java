package hi.cord.com.content.spam.service;

import hi.cord.com.common.domain.pagination.Pagination;
import hi.cord.com.common.service.abs.AbstractRestService;
import hi.cord.com.content.spam.domain.Spam;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRED, transactionManager = "shunTransactionManager", noRollbackFor = {NullPointerException.class})
public interface SpamService extends AbstractRestService<Spam> {

    Pagination<Spam> findAll(Spam spam, Pageable pageable);
}