package hi.cord.com.user.main.client.service;

import hi.cord.com.common.service.rest.CommonRestService;
import hi.cord.com.user.main.client.domain.User;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, noRollbackFor = {NullPointerException.class})
public interface UserService extends CommonRestService<User> {
    User findByEmail(String userEmail);

    User findByNickname(String nickname);

    User updateByNickname(User user);

    User deleteByEmail(String userEmail);

    User deleteByNickname(String nickname);
}