package hi.cord.com.jpa.user.servie.attempts;

import hi.cord.com.common.service.rest.CommonRestService;
import hi.cord.com.jpa.user.domain.UserAttempts;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
@Transactional(propagation=Propagation.REQUIRED, transactionManager="txManager", noRollbackFor={NullPointerException.class})
public interface UserAttemptsService extends CommonRestService<UserAttempts> {
	UserAttempts findByEmail(String email);

	UserAttempts findByNickname(String nickname);
}
