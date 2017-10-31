package hi.cord.com.jpa2.board.service;

import hi.cord.com.jpa2.board.domain.Board;
import hi.cord.com.jpa2.board.domain.BoardRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BoardServiceImpl implements BoardService {
    private static final Logger LOG = LoggerFactory.getLogger(BoardServiceImpl.class);
	private BoardRepository boardRepository;
	
	@Autowired
	public BoardServiceImpl(BoardRepository boardRepository) {
		this.boardRepository = boardRepository;
	}

	@Override
	public Board insert(Board board) {
		LOG.info("param : {}", board.toString());
		return boardRepository.save(board);
	}

    @Override
    public List<Board> findByList() {
        return boardRepository.findAll();
    }

    @Override
    public Page<Board> findByPage(Board board, Pageable pageable) {
        LOG.info("param : "+board.toString());
        return boardRepository.findAll(pageable);
    }

    @Override
    public Board findById(String id) {
        return null;
    }

    @Override
    public Board findById(long id) {
        return boardRepository.findById(id);
    }

    @Override
    public boolean deleteById(String id) {
	    return false;
    }

    @Override
    public boolean deleteById(long id) {
        Board dbBoard = boardRepository.findById(id);
        LOG.info("return : "+dbBoard);
        if (dbBoard == null) {
            return false;
        }

        boardRepository.delete(dbBoard.getId());
        return true;
    }

    @Override
    public Board update(Board board) {
        Board dbBoard = boardRepository.findById(board.getId());
        if (dbBoard != null) {
            dbBoard.setTitle(board.getTitle());
            dbBoard.setContent(board.getContent());
        }
        return dbBoard;
    }

    @Override
    public long count(Board board) {
        return boardRepository.countBy(board);
    }
}
