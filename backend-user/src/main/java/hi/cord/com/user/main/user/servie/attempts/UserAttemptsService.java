package hi.cord.com.user.main.user.servie.attempts;

import hi.cord.com.user.common.service.abs.AbstractRestService;
import hi.cord.com.user.main.user.domain.attempts.UserAttempts;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
@Transactional(propagation=Propagation.REQUIRED, transactionManager="txManager", noRollbackFor={NullPointerException.class})
public interface UserAttemptsService extends AbstractRestService<UserAttempts> {
	UserAttempts findByEmail(String email);
}
