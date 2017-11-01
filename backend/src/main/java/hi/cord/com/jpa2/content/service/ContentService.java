package hi.cord.com.jpa2.content.service;

import hi.cord.com.common.service.rest.AbstractRestService;
import hi.cord.com.jpa2.content.domain.Content;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation=Propagation.REQUIRED, transactionManager="shunTransactionManager", noRollbackFor={NullPointerException.class})
public interface ContentService extends AbstractRestService<Content> {

}
