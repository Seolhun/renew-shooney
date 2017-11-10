package hi.cord.com.user.main.user.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	List<User> findAllBy(User user);

	User findById(Long id);

	User findByEmail(String email);

	User findByNickname(String nickname);

	boolean deleteById(long id);

	User deleteByEmail(String email);

	long countBy(User user);

	long countDistinctBy(User user);
}

