package hi.cord.com.user.user.servie.attempts;

import hi.cord.com.common.service.abs.AbstractRestService;
import hi.cord.com.user.user.domain.attempts.UserAttempts;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
@Transactional(propagation=Propagation.REQUIRED, transactionManager="txManager", noRollbackFor={NullPointerException.class})
public interface UserAttemptsService extends AbstractRestService<UserAttempts> {
	UserAttempts findByEmail(String email);
}
