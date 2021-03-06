package hi.cord.com.user.main.profile.service;

import hi.cord.com.common.service.rest.CommonRestService;
import hi.cord.com.user.main.profile.domain.UserProfile;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, noRollbackFor = {NullPointerException.class})
public interface UserProfileService extends CommonRestService<UserProfile> {

	UserProfile findById(int id);

	UserProfile findByEmail(String email);

	UserProfile findByNickname(String nickname);

	UserProfile findByType(String type);
}
