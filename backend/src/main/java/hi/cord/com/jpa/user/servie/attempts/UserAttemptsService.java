package hi.cord.com.jpa.user.servie.attempts;

import hi.cord.com.jpa.user.domain.UserAttempts;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional(propagation=Propagation.REQUIRED, transactionManager="txManager", noRollbackFor={NullPointerException.class})
public interface UserAttemptsService {

	UserAttempts insert(UserAttempts userAttempts);

	UserAttempts findById(long id);
	
	UserAttempts findByEmail(String email);

	UserAttempts findByNickname(String nickname);
	
	List<UserAttempts> findAllBy(UserAttempts userAttempts);

	UserAttempts update(UserAttempts userAttempts);

	void delete(long id);
}
