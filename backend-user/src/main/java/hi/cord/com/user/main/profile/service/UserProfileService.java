package hi.cord.com.user.main.profile.service;

import hi.cord.com.common.service.abs.AbstractRestService;
import hi.cord.com.user.main.profile.domain.UserProfile;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
@Transactional(propagation=Propagation.REQUIRED, transactionManager="txManager", noRollbackFor={NullPointerException.class})
public interface UserProfileService extends AbstractRestService<UserProfile> {

	UserProfile findById(int id);

	UserProfile findByEmail(String email);

	UserProfile findByNickname(String nickname);

	UserProfile findByType(String type);
}
