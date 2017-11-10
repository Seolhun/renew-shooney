package hi.cord.com.content.main.comment.service;

import hi.cord.com.common.domain.pagination.Pagination;
import hi.cord.com.content.main.comment.domain.Comment;
import hi.cord.com.content.main.comment.domain.CommentRepository;
import hi.cord.com.content.main.content.domain.BlogContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED, transactionManager = "shunTransactionManager", noRollbackFor = {NullPointerException.class})
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private BlogContentRepository blogContentRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, BlogContentRepository blogContentRepository) {
        this.commentRepository = commentRepository;
        this.blogContentRepository = blogContentRepository;
    }

    @Override
    public Comment insert(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> findByList() {
        return commentRepository.findAll();
    }

    @Override
    public Page<Comment> findByPage(Comment comment, Pageable pageable) {
        return commentRepository.findAll(pageable);
    }

    @Override
    public Pagination<Comment> findAll(Comment comment, Pageable pageable) {
        // Call Repository        
        Page<Comment> comments = commentRepository.findAll(pageable);
        Pagination<Comment> pagination = new Pagination<>();

        pagination.setList(comments.getContent());
        pagination.setTotalCount(comments.getTotalElements());
        pagination.setPageIndex(pageable.getPageNumber());
        pagination.setPageSize(pageable.getPageSize());
        return pagination;
    }

    @Override
    public Comment findById(String id) {
        Comment comment = commentRepository.findById(id);
        return comment;
    }

    @Override
    public Comment findByIdx(long idx) {
        return null;
    }

    @Override
    public Comment findById(long id) {
        return null;
    }

    @Override
    public Comment findByIdx(long idx, String nickname) {
        return commentRepository.findByIdxAndCreatedByEntityNickname(idx, nickname);
    }

    @Override
    public boolean deleteById(String id, String accessBy) {
        Comment dbComment = commentRepository.findById(id);
        if (dbComment == null) {
            return false;
        }

        commentRepository.delete(dbComment.getId());
        return true;
    }

    @Override
    public boolean deleteById(long idx, String accessBy) {
        Comment dbComment = commentRepository.findByIdxAndCreatedByEntityNickname(idx, accessBy);
        if (dbComment == null) {
            return false;
        }

        commentRepository.delete(dbComment.getId());
        return true;
    }

    @Override
    public boolean deleteByIdx(long idx, String accessBy) {
        return false;
    }

    @Override
    public Comment updateById(Comment comment, String accessBy) {
        Comment dbComment = commentRepository.findById(comment.getId());
        if (dbComment != null) {

        }
        return dbComment;
    }

    @Override
    public long count(Comment comment) {
        return commentRepository.countBy(comment);
    }

    @Override
    public long getIdxByNickname(String nickname) {
        Comment comment = commentRepository.findFirstByCreatedByEntityNicknameOrderByIdxDesc(nickname);
        if (comment == null) {
            return 1;
        }

        return comment.getIdx() + 1;
    }
}
