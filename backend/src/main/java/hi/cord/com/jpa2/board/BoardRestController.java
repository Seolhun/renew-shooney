package hi.cord.com.jpa2.board;

import hi.cord.com.common.service.CommonService;
import hi.cord.com.jpa2.board.domain.Board;
import hi.cord.com.jpa2.board.service.BoardService;
import hi.cord.com.jpa2.file.service.FileDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
public class BoardRestController {
    static final Logger LOG = LoggerFactory.getLogger(BoardRestController.class);

    private BoardService boardService;
    private CommonService commonService;
    private CommentService commentService;
    private FileDataService fileDataService;

    @Autowired
    public BoardRestController(BoardService boardService, CommonService commonService, CommentService commentService, FileDataService fileDataService) {
        this.boardService = boardService;
        this.commonService = commonService;
        this.commentService = commentService;
        this.fileDataService = fileDataService;
    }

    /**
     * Board insert response entity.
     *
     * @param board the board
     * @param auth  the auth
     * @return AjaxResult ajax result
     *
     * @throws Exception the exception
     */
    @PostMapping(value = "/{nickname}/board")
    public ResponseEntity insert(@PathVariable String nickname, @RequestBody Board board, Authentication auth) throws Exception {
        if (board == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This is Null");
        }

        //게시판 저장.
        boardService.insert(board);
        return ResponseEntity.status(HttpStatus.OK).body("Success");
    }

    /**
     * Find all list.
     *
     * @param board the board
     * @return the list
     *
     * @throws Exception the exception
     */
    @GetMapping(value = {"/board"})
    public Page<Board> findAll(Board board) throws Exception {
        Page<Board> boardPage = boardService.findByPage(board, new PageRequest(0, 10));
        return boardPage;
    }


    /**
     * Find one board.
     *
     * @param boardId the board id
     * @return the board
     *
     * @throws Exception the exception
     */
    @GetMapping(value = "/board/{boardId}")
    public Board findOne(@PathVariable long boardId) throws Exception {
        Board dbBoard = boardService.findById(boardId);
        return dbBoard;
    }

    /**
     * Update response entity.
     *
     * @param nickname the nickname
     * @param boardId  the board id
     * @param board    the board
     * @return the response entity
     */
    @PutMapping(value = {"/{nickname}/{boardId}"})
    public ResponseEntity update(@PathVariable String nickname, @PathVariable long boardId, @Valid Board board) {
        return ResponseEntity.status(HttpStatus.OK).body("Fail");
    }

    /*----------- end 댓글 ---------------------------------------------------------------------*/
    private boolean checkHitCookie(Board board, HttpServletRequest request, HttpServletResponse response) {
        boolean validHit = false;
        long id = board.getId();
        String[] tableNames = request.getRequestURI().split("/");
        String tableName = tableNames[2];
        tableName = commonService.buildSHA256(tableName).substring(0, 5);

        // 쿠키 가져오기(체크 하는것)
        Map<String, String> cookieMap = new HashMap<>();
        Cookie cookies[] = request.getCookies();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                cookieMap.put(cookies[i].getName(), cookies[i].getValue());
            }
        }

        // 저장된 쿠키 중 읽었었던 해당 게시판의 번호 불러오기
        String originalNo = (String) cookieMap.get(tableName);
        Cookie cookie = null;
        if (originalNo == null) {
            cookie = new Cookie(tableName, String.valueOf(id));
        } else {
            String[] upHitByNo = originalNo.split("T3Y1");
            for (int i = 0; i < upHitByNo.length; i++) {
                if (upHitByNo[i].equals(id)) {
                    return validHit;
                }
            }
            originalNo = originalNo + "T3Y1" + id;
            cookie = new Cookie(tableName, originalNo);
        }

        // cookie.setPath("/imedisyn");
        cookie.setMaxAge(24 * 60 * 60); // 365*24*60*60 365일
        response.addCookie(cookie);
        validHit = true;
        return validHit;
    }
}