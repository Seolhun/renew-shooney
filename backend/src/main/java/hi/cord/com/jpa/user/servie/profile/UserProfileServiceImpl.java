package hi.cord.com.jpa.user.servie.profile;

import hi.cord.com.jpa.user.domain.UserProfile;
import hi.cord.com.jpa.user.repository.profile.UserProfileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userProfileService")
public class UserProfileServiceImpl implements UserProfileService {
	private static final Logger LOG = LoggerFactory.getLogger(UserProfileServiceImpl.class);
	

	private UserProfileRepository userProfileRepository;

	@Autowired
	public UserProfileServiceImpl(UserProfileRepository userProfileRepository) {
		this.userProfileRepository = userProfileRepository;
	}

	@Override
	public UserProfile findById(int id) {
		LOG.info("param : findById : {}", id);
		return userProfileRepository.findById(id);
	}

	@Override
	public UserProfile findByType(String type) {
		LOG.info("param : findByType : {}", type);
		return userProfileRepository.findByType(type);
	}

	@Override
	public List<UserProfile> findAll() {
		LOG.info("param : findAll");
		return userProfileRepository.findAll();
	}
}
