package hi.cord.com.user.common.comment.service;

import hi.cord.com.user.common.domain.pagination.Pagination;
import hi.cord.com.user.common.service.abs.AbstractRestService;
import hi.cord.com.user.common.comment.domain.Comment;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRED, transactionManager = "shunTransactionManager", noRollbackFor = {NullPointerException.class})
public interface CommentService extends AbstractRestService<Comment> {
    long getIdxByNickname(String nickname);

    Pagination<Comment> findAll(Comment content, Pageable pageable);

    Comment findByIdx(long idx, String nickname);
}