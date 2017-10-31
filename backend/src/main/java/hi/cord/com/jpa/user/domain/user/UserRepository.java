package hi.cord.com.jpa.user.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findById(Long id);

	User findByEmail(String email);

	User findByNickname(String nickname);

	boolean deleteById(long id);

	User deleteByEmail(String email);
}

