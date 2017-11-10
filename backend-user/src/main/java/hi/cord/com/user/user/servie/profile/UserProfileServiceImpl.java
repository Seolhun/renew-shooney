package hi.cord.com.user.user.servie.profile;

import hi.cord.com.user.user.domain.attempts.UserAttempts;
import hi.cord.com.user.user.domain.profile.UserProfile;
import hi.cord.com.user.user.domain.profile.UserProfileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
		LOG.debug("p : findById : {}", id);
		return userProfileRepository.findById(id);
	}

	@Override
	public UserAttempts findByEmail(String email) {
		return null;
	}

	@Override
	public UserAttempts findByNickname(String nickname) {
		return null;
	}

	@Override
	public UserProfile findByType(String type) {
		LOG.debug("p : findByType : {}", type);
		return userProfileRepository.findByType(type);
	}

	@Override
	public UserProfile insert(UserProfile userProfile) {
		return null;
	}

	@Override
	public List<UserProfile> findByList() {
		return userProfileRepository.findAll();
	}

	@Override
	public Page<UserProfile> findByPage(UserProfile userProfile, Pageable pageable) {
		return userProfileRepository.findAll(pageable);
	}

	@Override
	public UserProfile findById(String id) {
		return null;
	}

	@Override
	public UserProfile findById(long id) {
		return null;
	}

	@Override
	public UserProfile findByIdx(long idx) {
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
	public UserProfile updateById(UserProfile userProfile, String accessBy) {
		return null;
	}

	@Override
	public long count(UserProfile userProfile) {
		return 0;
	}
}