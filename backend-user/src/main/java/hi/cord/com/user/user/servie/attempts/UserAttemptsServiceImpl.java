package hi.cord.com.user.user.servie.attempts;

import hi.cord.com.user.user.domain.attempts.UserAttempts;
import hi.cord.com.user.user.domain.attempts.UserAttemptsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userAttemptsService")
public class UserAttemptsServiceImpl implements UserAttemptsService {
    private static final Logger LOG = LoggerFactory.getLogger(UserAttemptsServiceImpl.class);

    private UserAttemptsRepository userAttemptsRepository;

    @Autowired
    public UserAttemptsServiceImpl(UserAttemptsRepository userAttemptsRepository) {
        this.userAttemptsRepository = userAttemptsRepository;
    }

    @Override
    public UserAttempts insert(UserAttempts userAttempts) {
        LOG.debug("p : insert {}", userAttempts.toString());
        userAttemptsRepository.save(userAttempts);
        return userAttempts;
    }

    @Override
    public List<UserAttempts> findByList() {
        return null;
    }

    @Override
    public Page<UserAttempts> findByPage(UserAttempts userAttempts, Pageable pageable) {
        return null;
    }

    @Override
    public UserAttempts findById(String id) {
        return null;
    }

    @Override
    public UserAttempts findById(long id) {
        LOG.debug("p : findById", id);
        return userAttemptsRepository.findById(id);
    }

    @Override
    public UserAttempts findByIdx(long idx) {
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
    public UserAttempts findByEmail(String email) {
        LOG.debug("p : findByEmail {}", email);
        return userAttemptsRepository.findByEmail(email);
    }

    @Override
    public UserAttempts updateById(UserAttempts userAttempts, String accessBy) {
        LOG.debug("p : update" + userAttempts.toString());
        UserAttempts dbAttempts = userAttemptsRepository.findByEmail(userAttempts.getEmail());
        if (dbAttempts != null) {
            if (!(userAttempts.isSuccess())) {
                dbAttempts.setSuccess(userAttempts.isSuccess());
            }
        }
        return userAttempts;
    }

    @Override
    public long count(UserAttempts userAttempts) {
        return 0;
    }
}
