package hi.cord.com.user.main.privilege.domain;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EntityScan
public interface ProfilePrivilegeRepository extends JpaRepository<ProfilePrivilege, Long> {

	List<ProfilePrivilege> findAll();

	ProfilePrivilege findByType(String type);

	ProfilePrivilege findById(int id);

    boolean deleteById(long id);
}
