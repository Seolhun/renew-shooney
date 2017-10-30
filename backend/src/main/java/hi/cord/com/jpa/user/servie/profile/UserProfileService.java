package hi.cord.com.jpa.user.servie.profile;

import hi.cord.com.common.service.rest.CommonRestService;
import hi.cord.com.jpa.user.domain.UserAttempts;
import hi.cord.com.jpa.user.domain.UserProfile;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
@Transactional(propagation=Propagation.REQUIRED, transactionManager="txManager", noRollbackFor={NullPointerException.class})
public interface UserProfileService extends CommonRestService<UserProfile> {

	UserProfile findById(int id);

	UserAttempts findByEmail(String email);

	UserAttempts findByNickname(String nickname);

	UserProfile findByType(String type);
}
