package hi.cord.com.jpa.user.servie.user;

import hi.cord.com.jpa.user.domain.User;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(propagation=Propagation.REQUIRED, transactionManager="txManager", noRollbackFor={NullPointerException.class})
public interface UserService {

	User findById(long usreId);

	User findByEmail(String userEmail);
	
	User findByNickname(String nickname);

	User insert(User user);

	User update(User user);

	User deleteByEmail(String userEmail);

	List<User> findAll();
}