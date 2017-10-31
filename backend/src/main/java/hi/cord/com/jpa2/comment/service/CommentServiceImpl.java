package hi.cord.com.jpa2.comment.service;

import hi.cord.com.jpa2.board.domain.Board;
import hi.cord.com.jpa2.board.domain.BoardRepository;
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
	private BoardRepository boardRepository;
	
	@Autowired
	public CommentServiceImpl(CommentRepository commentRepository, BoardRepository boardRepository) {
		this.commentRepository=commentRepository;
		this.boardRepository = boardRepository;
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
	public boolean deleteById(String id) {
		return false;
	}

	@Override
	public boolean deleteById(long id) {
		return false;
	}

	@Override
	public Comment update(Comment comment) {
		Comment dbComment = commentRepository.findById(comment.getId());
		String createdBy=dbComment.getCreatedBy();
		String modifyBy=comment.getModifiedBy();
		if(createdBy.equals(modifyBy)){
			if(comment.isActive()){
				dbComment.setActive(false);
				Board dbBoard= boardRepository.findById(dbComment.getBoardInComment().getId());
				//읽을시 쿠키 읽기
				if(dbBoard != null){
					int depth=dbBoard.getDepth();
					dbBoard.setDepth(depth-1);
				}
			} else if(comment.getContent()!=null){
				dbComment.setContent(comment.getContent());
			}
			dbComment.setModifiedBy(comment.getModifiedBy());
		}
		return dbComment;
	}

	@Override
	public long count(Comment comment) {
		return 0;
	}
}
