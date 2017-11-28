package hi.cord.com.content.main.tag.service;

import hi.cord.com.common.domain.pagination.Pagination;
import hi.cord.com.content.main.tag.domain.Tag;
import hi.cord.com.content.main.tag.domain.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
public class TagServiceImpl implements TagService {

    private TagRepository tagRepository;

    @Autowired
    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public Tag insert(Tag tag) {
        return tagRepository.save(tag);
    }

    @Override
    public List<Tag> findByList() {
        return null;
    }

    @Override
    public Page<Tag> findByPage(Tag tag, Pageable pageable) {
        return tagRepository.findAll(pageable);
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
    public Tag updateByIdx(Tag tag, String accessBy) {
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

    @Override
    public <S extends Tag> List<S> saveIterable(Iterable<S> tags) {
        return tagRepository.save(tags);
    }

    @Override
    public Tag findByName(String tagName) {
        return tagRepository.findByName(tagName);
    }

    @Override
    public Pagination<Tag> findAll(Example<Tag> tagExample, Pageable pageable) {
        Page<Tag> tags = tagRepository.findAll(tagExample, pageable);

        Pagination<Tag> pagination = new Pagination<>();
        pagination.setList(tags.getContent());
        pagination.setTotalCount(tags.getTotalElements());
        pagination.setPageIndex(pageable.getPageNumber());
        pagination.setPageSize(pageable.getPageSize());
        return pagination;
    }
}
