package hi.cord.com.jpa.user.domain.attempts;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@EntityScan
public interface UserAttemptsRepository extends JpaRepository<UserAttempts, Long>{
	UserAttempts findById(long id);

	UserAttempts findByEmail(String email);

	boolean deleteById(long id);
}
