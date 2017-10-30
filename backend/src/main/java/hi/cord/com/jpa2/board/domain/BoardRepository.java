package hi.cord.com.jpa2.board.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long>{
	
	List<Board> findAllBy(Board board);
	
	Board findById(long id);

	Board insert(Board board);

	void delete(long id);
	
	long countBy(Board board);

	long countDistinctBy(Board board);
}
