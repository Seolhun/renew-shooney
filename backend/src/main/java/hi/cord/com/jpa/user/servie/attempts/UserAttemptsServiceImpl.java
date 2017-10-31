package hi.cord.com.jpa.user.servie.attempts;

import hi.cord.com.jpa.user.domain.attempts.UserAttempts;
import hi.cord.com.jpa.user.domain.attempts.UserAttemptsRepository;
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
		LOG.info("param : insert {}", userAttempts.toString());
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
		LOG.info("param : findById", id);
		return userAttemptsRepository.findById(id);
	}

	@Override
	public UserAttempts findByIdx(long idx) {
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
	public UserAttempts findByEmail(String email) {
		LOG.info("param : findByEmail {}", email);
		return userAttemptsRepository.findByEmail(email);
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
	public long count(UserAttempts userAttempts) {
		return 0;
	}
}
