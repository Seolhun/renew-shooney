package hi.cord.com.jpa2.content;

import hi.cord.com.common.service.CommonService;
import hi.cord.com.jpa2.comment.service.CommentService;
import hi.cord.com.jpa2.content.domain.Content;
import hi.cord.com.jpa2.content.service.ContentService;
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
import java.util.List;
import java.util.Map;


/**
 * The type Content rest controller.
 */
@RequestMapping("/content")
@RestController
public class ContentRestController {
    static final Logger LOG = LoggerFactory.getLogger(ContentRestController.class);

    private ContentService contentService;
    private CommonService commonService;
    private CommentService commentService;
    private FileDataService fileDataService;

    @Autowired
    public ContentRestController(ContentService contentService, CommonService commonService, CommentService commentService, FileDataService fileDataService) {
        this.contentService = contentService;
        this.commonService = commonService;
        this.commentService = commentService;
        this.fileDataService = fileDataService;
    }

    /**
     * Find all response entity.
     *
     * @return the response entity
     */
    @GetMapping("")
    public ResponseEntity findAll(Content content){


        return ResponseEntity.status(HttpStatus.OK).body("Success");
    }

    /**
     * Save response entity.
     *
     * @return the response entity
     */
    @PostMapping("")
    public ResponseEntity save(Content content){
        if(content == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not found \"content\" parameter");
        }
        List<String> images = commonService.extractImgSrc(content.getContent());
        for (String img:images) {
            //Convert Img > File > return File ID;

        }
        // Requirement Images >

        contentService.insert(content);
        LOG.debug("p : save content {}", content.toString());
        return ResponseEntity.status(HttpStatus.OK).body("Success");
    }


    /**
     * Find one response entity.
     *
     * @return the response entity
     */
    @GetMapping("/{nickname}/{contentId}")
    public ResponseEntity findOne(@PathVariable String nickname, @PathVariable String contentId, Content content){
        if(content == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not found \"content\" parameter");
        }

        return ResponseEntity.status(HttpStatus.OK).body("Success");
    }

    /**
     * Updated response entity.
     *
     * @return the response entity
     */
    @PutMapping("/{nickname}/{contentId}")
    public ResponseEntity updated(@PathVariable String nickname, @PathVariable String contentId, Content content){
        if(content == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not found \"content\" parameter");
        }

        return ResponseEntity.status(HttpStatus.OK).body("Success");
    }

    /**
     * Delete response entity.
     *
     * @return the response entity
     */
    @DeleteMapping("/{nickname}/{contentId}")
    public ResponseEntity delete(@PathVariable String nickname, @PathVariable String contentId){

        return ResponseEntity.status(HttpStatus.OK).body("Success");
    }

    /*----------- end 댓글 ---------------------------------------------------------------------*/
    private boolean checkHitCookie(Content content, HttpServletRequest request, HttpServletResponse response) {
        boolean validHit = false;
        String id = content.getId();
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