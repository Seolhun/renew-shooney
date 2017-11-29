package hi.cord.com.content.main.content;

import hi.cord.com.common.domain.pagination.Pagination;
import hi.cord.com.common.service.CommonService;
import hi.cord.com.content.main.comment.service.CommentService;
import hi.cord.com.content.main.content.domain.BlogContent;
import hi.cord.com.content.main.content.service.BlogContentService;
import hi.cord.com.content.main.file.service.FileDataService;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;


/**
 * The type BlogContent rest controller.
 */
@CrossOrigin(origins = {"http://localhost:7000", "http://localhost:5000", "http://127.0.0.1:7000", "http://127.0.0.1:5000"})
@RequestMapping("/content")
@RestController
public class ContentRestController {
    private static final Logger LOG = LoggerFactory.getLogger(ContentRestController.class);

    private BlogContentService blogContentService;
    private CommonService commonService;
    private CommentService commentService;
    private FileDataService fileDataService;

    @Autowired
    public ContentRestController(BlogContentService blogContentService, CommonService commonService, CommentService commentService, FileDataService fileDataService) {
        this.blogContentService = blogContentService;
        this.commonService = commonService;
        this.commentService = commentService;
        this.fileDataService = fileDataService;
    }

    /**
     * Find all response entity.
     *
     * @return the response entity
     */
    @GetMapping(value = "")
    public ResponseEntity findAll(
            BlogContent blogContent,
            @RequestParam(defaultValue = "0") Integer pageIndex,
            @RequestParam(defaultValue = "15") Integer pageSize
    ) {
        if (pageIndex == null) {
            pageIndex = 0;
        } else if (pageSize == null) {
            pageSize = 15;
        }

        //Pagination and FindAll
        Pageable pageable = new PageRequest(pageIndex, pageSize);
        Example<BlogContent> blogContentExample = Example.of(blogContent);
        Pagination<BlogContent> contentPagination = blogContentService.findAll(blogContentExample, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(contentPagination);
    }

    /**
     * Save response entity.
     *
     * @return the response entity
     */
    // @Async("contentTaskExecutor")
    @PostMapping("")
    public ResponseEntity insert(
            @Valid @RequestBody BlogContent blogContent,
            Errors errors
    ) throws FileUploadException {
        // POST Parameter Validation
        if (blogContent == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not found blogContent parameter");
        } else if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getFieldError().getDefaultMessage());
        }

        //Setting Index
        long idx = blogContentService.getIdxByNickname(blogContent.getBaseCreatedBy().getCreatedByNickname());
        blogContent.setIdx(idx);

        // Insert
        blogContentService.insert(blogContent);
        return ResponseEntity.status(HttpStatus.OK).body(blogContent);
    }


    /**
     * Find one response entity.
     *
     * @return the response entity
     */
    @GetMapping("/{nickname}/{idx}")
    public ResponseEntity findOne(
            @PathVariable String nickname,
            @PathVariable Long idx
    ) {
        if (nickname == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not found nickname path");
        } else if (idx == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not found BlogContent-Identification path");
        }

        BlogContent blogContent = blogContentService.findByIdx(idx, nickname);
        if (blogContent == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not found blogContent result");
        }
        return ResponseEntity.status(HttpStatus.OK).body(blogContent);
    }

    /**
     * Updated response entity.
     *
     * @return the response entity
     */
    @PutMapping("/{nickname}/{idx}")
    public ResponseEntity updated(
            BlogContent blogContent,
            @PathVariable String nickname,
            @PathVariable Long idx
    ) {
        if (blogContent == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not found required blogContent parameter");
        } else if (nickname == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not found nickname path");
        } else if (idx == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not found BlogContent-Identification path");
        }

        blogContent = blogContentService.updateById(blogContent, nickname);
        if (blogContent == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not found blogContent result");
        } else if (!(blogContent.getBaseCreatedBy().getCreatedByNickname().equals(nickname))) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("MissMatch created by and access user");
        }

        return ResponseEntity.status(HttpStatus.OK).body(blogContent);
    }

    /**
     * Delete response entity.
     *
     * @return the response entity
     */
    @DeleteMapping("/{nickname}/{idx}")
    public ResponseEntity delete(
            @PathVariable String nickname,
            @PathVariable Long idx,
            Principal principal
    ) {
        if (principal.getName().equals(nickname)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not matched created By and accessed user");
        } else if (nickname == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not found nickname path");
        } else if (idx == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not found BlogContent-Identification path");
        }

        if (!(blogContentService.deleteByIdx(idx, nickname))) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not found content result");
        }

        return ResponseEntity.status(HttpStatus.OK).body("Success");
    }


    /**
     * #####################################################################
     * ###########################  Method Part  ###########################
     * #####################################################################
     */
    /*----------- end 댓글 ---------------------------------------------------------------------*/
    private boolean checkHitCookie(BlogContent blogContent, HttpServletRequest request, HttpServletResponse response) {
        String id = blogContent.getId();
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
            for (String anUpHitByNo : upHitByNo) {
                if (anUpHitByNo.equals(id)) {
                    return false;
                }
            }
            originalNo = originalNo + "T3Y1" + id;
            cookie = new Cookie(tableName, originalNo);
        }

        // cookie.setPath("/imedisyn");
        cookie.setMaxAge(24 * 60 * 60); // 365*24*60*60 365일
        response.addCookie(cookie);
        return true;
    }
}