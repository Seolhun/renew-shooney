package hi.cord.com.content.main.spam.service;

import hi.cord.com.common.domain.pagination.Pagination;
import hi.cord.com.content.main.spam.domain.Spam;
import hi.cord.com.content.main.spam.domain.SpamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
public class SpamServiceImpl implements SpamService {

    private SpamRepository spamRepository;

    @Autowired
    public SpamServiceImpl(SpamRepository spamRepository) {
        this.spamRepository = spamRepository;
    }

    @Override
    public Spam insert(Spam spam) {
        return spamRepository.save(spam);
    }

    @Override
    public List<Spam> findByList() {
        return null;
    }

    @Override
    public Page<Spam> findByPage(Spam spam, Pageable pageable) {
        return spamRepository.findAll(pageable);
    }

    @Override
    public Spam findById(String id) {
        return null;
    }

    @Override
    public Spam findById(long id) {
        return null;
    }

    @Override
    public Spam findByIdx(long idx) {
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
    public Spam updateById(Spam spam, String accessBy) {
        return null;
    }

    @Override
    public Spam updateByIdx(Spam spam, String accessBy) {
        return null;
    }

    @Override
    public long count(Spam spam) {
        return 0;
    }

    @Override
    public Pagination<Spam> findAll(Spam spam, Pageable pageable) {
        return null;
    }
}
