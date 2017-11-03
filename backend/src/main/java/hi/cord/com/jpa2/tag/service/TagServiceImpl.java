//package hi.cord.com.jpa2.tag.service;
//
//import hi.cord.com.jpa2.content.domain.Content;
//import hi.cord.com.jpa2.content.domain.ContentRepository;
//import hi.cord.com.jpa2.tag.domain.Tag;
//import hi.cord.com.jpa2.tag.domain.TagRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Propagation;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//@Service
//@Transactional(propagation= Propagation.REQUIRED, transactionManager="shunTransactionManager", noRollbackFor={NullPointerException.class})
//public class TagServiceImpl implements TagService {
//
//	private TagRepository commentRepository;
//	private ContentRepository contentRepository;
//
//	@Autowired
//	public TagServiceImpl(TagRepository commentRepository, ContentRepository contentRepository) {
//		this.commentRepository=commentRepository;
//		this.contentRepository = contentRepository;
//	}
//
//	@Override
//	public Tag insert(Tag comment) {
//		return commentRepository.save(comment);
//	}
//
//	@Override
//	public List<Tag> findByList() {
//		return null;
//	}
//
//	@Override
//	public Page<Tag> findByPage(Tag comment, Pageable pageable) {
//		return commentRepository.findAll(pageable);
//	}
//
//	@Override
//	public Tag findById(String id) {
//		return null;
//	}
//
//	@Override
//	public Tag findById(long id) {
//		return null;
//	}
//
//	@Override
//	public Tag findByIdx(long idx) {
//		return null;
//	}
//
//	@Override
//	public boolean deleteById(String id) {
//		return false;
//	}
//
//	@Override
//	public boolean deleteById(long id) {
//		return false;
//	}
//
//	@Override
//	public boolean deleteByIdx(long idx) {
//		return false;
//	}
//
//	@Override
//	public Tag updateById(Tag comment) {
//		return dbComment;
//	}
//
//	@Override
//	public long count(Tag comment) {
//		return 0;
//	}
//}
