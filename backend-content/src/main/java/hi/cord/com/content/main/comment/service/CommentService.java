package hi.cord.com.content.main.comment.service;

import hi.cord.com.common.domain.pagination.Pagination;
import hi.cord.com.common.service.rest.CommonRestService;
import hi.cord.com.content.main.comment.domain.Comment;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRED, transactionManager = "shunTransactionManager", noRollbackFor = {NullPointerException.class})
public interface CommentService extends CommonRestService<Comment> {
    long getIdxByNickname(String nickname);

    Pagination<Comment> findAll(Comment content, Pageable pageable);

    Comment findByIdx(long idx, String nickname);
}