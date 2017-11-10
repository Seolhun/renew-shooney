package hi.cord.com.user.main.user.servie.user;

import hi.cord.com.user.common.service.abs.AbstractRestService;
import hi.cord.com.user.main.user.domain.user.User;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRED, transactionManager = "txManager", noRollbackFor = {NullPointerException.class})
public interface UserService extends AbstractRestService<User> {
    User findByEmail(String userEmail);

    User findByNickname(String nickname);

    User updateByNickname(User user);

    User deleteByEmail(String userEmail);

    User deleteByNickname(String nickname);
}