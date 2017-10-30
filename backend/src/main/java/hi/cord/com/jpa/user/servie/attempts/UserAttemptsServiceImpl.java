package hi.cord.com.jpa.user.servie.attempts;

import hi.cord.com.jpa.user.domain.UserAttempts;
import hi.cord.com.jpa.user.repository.attempts.UserAttemptsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
		LOG.info("param : insert {}", userAttempts.toString());
		userAttemptsRepository.insert(userAttempts);
		return userAttempts;
	}

	@Override
	public UserAttempts findById(long id) {
		LOG.info("param : findById", id);
		return userAttemptsRepository.findById(id);
	}
	
	@Override
	public UserAttempts findByEmail(String email) {
		LOG.info("param : findByEmail {}", email);
		return userAttemptsRepository.findByEmail(email);
	}

    @Override
    public UserAttempts findByNickname(String nickname) {
        LOG.info("param : findByNickname {}", nickname);
        return userAttemptsRepository.findByNickname(nickname);
    }

	@Override
	public List<UserAttempts> findAllBy(UserAttempts userAttempts) {
		LOG.info("param : findAll {}", userAttempts.toString());
		return userAttemptsRepository.findAllBy(userAttempts);
	}

	@Override
	public UserAttempts update(UserAttempts userAttempts) {
		LOG.info("param : update"+userAttempts.toString());
		UserAttempts dbAttempts = userAttemptsRepository.findByEmail(userAttempts.getEmail());
		if (dbAttempts != null) {
			if(!(userAttempts.isSuccess())) {
				dbAttempts.setSuccess(userAttempts.isSuccess());
			}
		}
		return userAttempts;
	}

    @Override
    public void delete(long id) {
        userAttemptsRepository.delete(id);
    }
}
