package hi.cord.com.jpa.user.servie.user;

import hi.cord.com.common.service.rest.CommonRestService;
import hi.cord.com.jpa.user.domain.User;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation=Propagation.REQUIRED, transactionManager="txManager", noRollbackFor={NullPointerException.class})
public interface UserService extends CommonRestService<User> {
	User findByEmail(String userEmail);
	
	User findByNickname(String nickname);

	User deleteByEmail(String userEmail);

	User deleteByNickname(String nickname);
}