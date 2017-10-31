package hi.cord.com.jpa2.comment.service;

import hi.cord.com.jpa2.content.domain.Content;
import hi.cord.com.jpa2.content.domain.ContentRepository;
import hi.cord.com.jpa2.comment.domain.Comment;
import hi.cord.com.jpa2.comment.domain.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation= Propagation.REQUIRED, transactionManager="shunTransactionManager", noRollbackFor={NullPointerException.class})
public class CommentServiceImpl implements CommentService {

	private CommentRepository commentRepository;
	private ContentRepository contentRepository;
	
	@Autowired
	public CommentServiceImpl(CommentRepository commentRepository, ContentRepository contentRepository) {
		this.commentRepository=commentRepository;
		this.contentRepository = contentRepository;
	}
	
	@Override
	public Comment insert(Comment comment) {
		return commentRepository.save(comment);
	}

	@Override
	public List<Comment> findByList() {
		return null;
	}

	@Override
	public Page<Comment> findByPage(Comment comment, Pageable pageable) {
		return commentRepository.findAll(pageable);
	}

	@Override
	public Comment findById(String id) {
		return null;
	}

	@Override
	public Comment findById(long id) {
		return null;
	}

	@Override
	public Comment findByIdx(long idx) {
		return null;
	}

	@Override
	public boolean deleteById(String id) {
		return false;
	}

	@Override
	public boolean deleteById(long id) {
		return false;
	}

	@Override
	public boolean deleteByIdx(long idx) {
		return false;
	}

	@Override
	public Comment update(Comment comment) {
		Comment dbComment = commentRepository.findById(comment.getId());
		String createdBy=dbComment.getCreatedByEntity().getNickname();
		String modifyBy=comment.getModifiedByEntity().getNickname();
		if(createdBy.equals(modifyBy)){
			if(comment.isActive()){
				dbComment.setActive(false);
				Content dbContent = contentRepository.findById(dbComment.getContentInContent().getId());
				//읽을시 쿠키 읽기
				if(dbContent != null){
					int depth= dbContent.getDepth();
					dbContent.setDepth(depth-1);
				}
			} else if(comment.getContent()!=null){
				dbComment.setContent(comment.getContent());
			}
			dbComment.setModifiedByEntity(comment.getModifiedByEntity());
		}
		return dbComment;
	}

	@Override
	public long count(Comment comment) {
		return 0;
	}
}
