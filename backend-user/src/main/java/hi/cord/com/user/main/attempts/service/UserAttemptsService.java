package hi.cord.com.user.main.attempts.service;

import hi.cord.com.common.service.rest.CommonRestService;
import hi.cord.com.user.main.attempts.domain.UserAttempts;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, noRollbackFor = {NullPointerException.class})
public interface UserAttemptsService extends CommonRestService<UserAttempts> {
	UserAttempts findByEmail(String email);
}
