package hi.cord.com.jpa.user.servie.profile;

import hi.cord.com.common.service.abs.AbstractRestService;
import hi.cord.com.jpa.user.domain.attempts.UserAttempts;
import hi.cord.com.jpa.user.domain.profile.UserProfile;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
@Transactional(propagation=Propagation.REQUIRED, transactionManager="txManager", noRollbackFor={NullPointerException.class})
public interface UserProfileService extends AbstractRestService<UserProfile> {

	UserProfile findById(int id);

	UserAttempts findByEmail(String email);

	UserAttempts findByNickname(String nickname);

	UserProfile findByType(String type);
}
