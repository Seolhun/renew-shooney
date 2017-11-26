package hi.cord.com.content.main.tag.service;

import hi.cord.com.common.service.rest.CommonRestService;
import hi.cord.com.content.main.tag.domain.Tag;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation= Propagation.REQUIRED, transactionManager="shunTransactionManager", noRollbackFor={NullPointerException.class})
public interface TagService extends CommonRestService<Tag> {

}