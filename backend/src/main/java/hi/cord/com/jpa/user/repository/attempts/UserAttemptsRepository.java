package hi.cord.com.jpa.user.repository.attempts;

import hi.cord.com.jpa.user.domain.UserAttempts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserAttemptsRepository extends JpaRepository<UserAttempts, Long>{

	UserAttempts insert(UserAttempts userAttempts);
	
	UserAttempts findById(long id);

	UserAttempts findByEmail(String email);

	UserAttempts findByNickname(String nickname);

	List<UserAttempts> findAllBy(UserAttempts userAttempts);
}
