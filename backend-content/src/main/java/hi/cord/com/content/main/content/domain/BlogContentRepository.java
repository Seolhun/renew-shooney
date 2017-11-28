package hi.cord.com.content.main.content.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogContentRepository extends JpaRepository<BlogContent, String>{
	List<BlogContent> findAllBy(BlogContent blogContent);

	BlogContent findById(String id);

    BlogContent findByIdxAndBaseCreatedByCreatedByNickname(long idx, String nickname);

    //Get Sequence
    BlogContent findFirstByBaseCreatedByCreatedByNicknameOrderByIdxDesc(String nickname);

	boolean deleteById(String id);

	boolean deleteByIdx(long idx);

	long countBy(BlogContent blogContent);

	long countDistinctBy(BlogContent blogContent);
}
