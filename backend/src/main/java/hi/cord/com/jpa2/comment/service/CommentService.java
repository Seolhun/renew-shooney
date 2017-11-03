package hi.cord.com.jpa2.comment.service;

import hi.cord.com.common.service.abs.AbstractRestService;
import hi.cord.com.jpa2.comment.domain.Comment;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation= Propagation.REQUIRED, transactionManager="shunTransactionManager", noRollbackFor={NullPointerException.class})
public interface CommentService extends AbstractRestService<Comment> {
}