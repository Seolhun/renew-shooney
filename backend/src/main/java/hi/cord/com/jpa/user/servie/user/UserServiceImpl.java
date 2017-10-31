package hi.cord.com.jpa.user.servie.user;

import hi.cord.com.common.domain.CommonState;
import hi.cord.com.jpa.user.domain.user.User;
import hi.cord.com.jpa.user.domain.user.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
	private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;

	@Autowired
	public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public User findById(long id) {
		LOG.info("param : findById : {} ", id);
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
	public User findByEmail(String userEmail) {
		LOG.info("param : findByEmail : {} ", userEmail);
		return userRepository.findByEmail(userEmail);
	}

	@Override
	public User findByNickname(String nickname) {
		return null;
	}

	@Override
	public User insert(User user) {
		LOG.info("param : save : {} ", user.toString());
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setState(CommonState.ACTIVE);
		return userRepository.save(user);
	}

	@Override
	public List<User> findByList() {
		return null;
	}

	@Override
	public Page<User> findByPage(User user, Pageable pageable) {
		return null;
	}

	@Override
	public User findById(String id) {
		return null;
	}

	@Override
	public User update(User user) {
		LOG.info("param : update : {} ", user.toString());
		User dbUser = userRepository.findByEmail(user.getEmail());
		if (dbUser != null) {
			dbUser.setName(user.getName());
		}
		return user;
	}

	@Override
	public long count(User user) {
		return 0;
	}

	@Override
	public User deleteByEmail(String userEmail) {
		LOG.info("param : deleteUserByEmail : {} ", userEmail);
		return userRepository.deleteByEmail(userEmail);
	}

	@Override
	public User deleteByNickname(String nickname) {
		return null;
	}
}