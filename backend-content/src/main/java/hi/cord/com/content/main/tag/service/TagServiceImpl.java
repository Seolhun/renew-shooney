package hi.cord.com.content.main.tag.service;

import hi.cord.com.content.main.content.domain.BlogContentRepository;
import hi.cord.com.content.main.tag.domain.Tag;
import hi.cord.com.content.main.tag.domain.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
public class TagServiceImpl implements TagService {

    private TagRepository commentRepository;
    private BlogContentRepository contentRepository;

    @Autowired
    public TagServiceImpl(TagRepository commentRepository, BlogContentRepository contentRepository) {
        this.commentRepository = commentRepository;
        this.contentRepository = contentRepository;
    }

    @Override
    public Tag insert(Tag comment) {
        return commentRepository.save(comment);
    }

    @Override
    public List<Tag> findByList() {
        return null;
    }

    @Override
    public Page<Tag> findByPage(Tag comment, Pageable pageable) {
        return commentRepository.findAll(pageable);
    }

    @Override
    public Tag findById(String id) {
        return null;
    }

    @Override
    public Tag findById(long id) {
        return null;
    }

    @Override
    public boolean deleteById(String id, String accessBy) {
        return false;
    }

    @Override
    public boolean deleteById(long id, String accessBy) {
        return false;
    }

    @Override
    public boolean deleteByIdx(long idx, String accessBy) {
        return false;
    }

    @Override
    public Tag updateById(Tag tag, String accessBy) {
        return null;
    }

    @Override
    public Tag findByIdx(long idx) {
        return null;
    }

    @Override
    public long count(Tag comment) {
        return 0;
    }
}
