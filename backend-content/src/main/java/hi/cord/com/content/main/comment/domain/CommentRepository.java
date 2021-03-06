package hi.cord.com.content.main.comment.domain;

import hi.cord.com.content.main.content.domain.BlogContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, String> {
    List<Comment> findAllBy(Comment comment);

    Comment findById(String id);

    Comment findByIdxAndBaseCreatedByCreatedByNickname(long idx, String nickname);

    //Get Sequence
    Comment findFirstByBaseCreatedByCreatedByNicknameOrderByIdxDesc(String nickname);

    boolean deleteById(String id);

    boolean deleteByIdx(long idx);

    long countBy(Comment comment);

    long countDistinctBy(Comment comment);
}
