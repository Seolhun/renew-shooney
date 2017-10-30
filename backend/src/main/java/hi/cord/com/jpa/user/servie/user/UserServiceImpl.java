package hi.cord.com.jpa.user.servie.user;

import hi.cord.com.common.domain.CommonState;
import hi.cord.com.jpa.user.domain.User;
import hi.cord.com.jpa.user.repository.user.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userService")
@Transactional
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
		return userRepository.findById(id);
	}
	
	@Override
	public User findByEmail(String userEmail) {
		LOG.info("param : findByEmail : {} ", userEmail);
		return userRepository.findByEmail(userEmail);
	}
	
	@Override
	public User findByPhone(String userPhone) {
		LOG.info("param : findByPhone : {} ", userPhone);
		return userRepository.findByPhone(userPhone);
	}
	
	@Override
	public User insert(User user) {
		LOG.info("param : save : {} ", user.toString());
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setState(CommonState.ACTIVE);
		return userRepository.save(user);
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
	public User deleteByEmail(String userEmail) {
		LOG.info("param : deleteUserByEmail : {} ", userEmail);
		return userRepository.deleteByEmail(userEmail);
	}
	
	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}
}