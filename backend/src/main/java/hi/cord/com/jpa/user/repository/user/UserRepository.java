package hi.cord.com.jpa.user.repository.user;

import hi.cord.com.jpa.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

	User findById(Long id);
	
	User findByEmail(String email);

	User findByNickname(String nickname);

	User deleteByEmail(String email);
	
	List<User> findAll(User user);
}

