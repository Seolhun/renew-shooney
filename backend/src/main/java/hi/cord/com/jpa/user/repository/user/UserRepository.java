package hi.cord.com.jpa.user.repository.user;

import hi.cord.com.jpa.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findById(Long id);

	User findByEmail(String email);

	User findByNickname(String nickname);

	User deleteByEmail(String email);

	List<User> findAll(User user);
}

