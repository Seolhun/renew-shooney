package hi.cord.com.jpa.user.servie.attempts;

import hi.cord.com.common.service.rest.AbstractRestService;
import hi.cord.com.jpa.user.domain.attempts.UserAttempts;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
@Transactional(propagation=Propagation.REQUIRED, transactionManager="txManager", noRollbackFor={NullPointerException.class})
public interface UserAttemptsService extends AbstractRestService<UserAttempts> {
	UserAttempts findByEmail(String email);
}
