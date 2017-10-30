package hi.cord.com.jpa.user.repository.attempts;

import hi.cord.com.jpa.user.domain.UserAttempts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAttemptsRepository extends JpaRepository<UserAttempts, Long>{
	UserAttempts findById(long id);

	UserAttempts findByEmail(String email);

	UserAttempts findByNickname(String nickname);
}
