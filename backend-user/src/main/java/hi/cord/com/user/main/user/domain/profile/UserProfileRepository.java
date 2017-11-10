package hi.cord.com.user.main.user.domain.profile;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EntityScan
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

	List<UserProfile> findAll();

	UserProfile findByType(String type);

	UserProfile findById(int id);

    boolean deleteById(long id);
}
