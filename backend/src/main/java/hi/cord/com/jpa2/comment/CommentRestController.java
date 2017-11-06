package hi.cord.com.jpa2.comment;

import hi.cord.com.common.domain.pagination.Pagination;
import hi.cord.com.common.service.common.CommonService;
import hi.cord.com.jpa2.comment.domain.Comment;
import hi.cord.com.jpa2.comment.service.CommentService;
import hi.cord.com.jpa2.content.domain.Content;
import hi.cord.com.jpa2.content.service.ContentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * The type Content rest controller.
 */
@CrossOrigin(origins = "/**", maxAge = 3600)
@RequestMapping("/comment")
@RestController
public class CommentRestController {
    private static final Logger LOG = LoggerFactory.getLogger(CommentRestController.class);

    private CommonService commonService;
    private ContentService contentService;
    private CommentService commentService;

    @Autowired
    public CommentRestController(CommonService commonService, ContentService contentService, CommentService commentService) {
        this.commonService = commonService;
        this.contentService = contentService;
        this.commentService = commentService;
    }


    /**
     * Find all response entity.
     *
     * @return the response entity
     */
    @GetMapping(value = "/{nickname}/{commentIdx}")
    public ResponseEntity findAll(
            Comment comment,
            @PathVariable String nickname,
            @PathVariable Long commentIdx,
            @RequestParam(required = false) Integer pageIndex,
            @RequestParam(required = false) Integer pageSize
    ) {
        //Pagination and FindAll
        Pageable pageable = commonService.getPageable(pageIndex, pageSize);
        Pagination<Comment> commentPagination = commentService.findAll(comment, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(commentPagination);
    }

    /**
     * Save response entity.
     *
     * @return the response entity
     */
    @PostMapping(value = "/{contentCreatedBy}/{contentIdx}")
    public ResponseEntity insert(
            @PathVariable String contentCreatedBy,
            @PathVariable Long contentIdx,
            @Valid @RequestBody Comment comment,
            Principal principal,
            Errors errors
    ) {
        if (comment == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not found \"comment\" parameter");
        } else if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getFieldError().getDefaultMessage());
        }

        //Setting Comment Index
        long idx = commentService.getIdxByNickname(principal.getName());
        comment.setIdx(idx);

        //Seting Content relational with Comment
        Content content = new Content();
        content.setIdx(contentIdx);
        content.getCreatedByEntity().setNickname(contentCreatedBy);
        comment.setContentInComment(content);

        // Insert
        commentService.insert(comment);
        LOG.debug("p : save comment {}", comment.toString());
        return ResponseEntity.status(HttpStatus.OK).body("Success");
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
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not found \"nickname\" path");
        } else if (idx == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not found \"Content-Identification\" path");
        }

        Comment comment = commentService.findByIdx(idx, nickname);
        if (comment == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not found \"comment\" result");
        } else if (!(comment.getCreatedByEntity().getNickname().equals(nickname))) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Missmatch created by and access user");
        }

        return ResponseEntity.status(HttpStatus.OK).body(comment);
    }

    /**
     * Updated response entity.
     *
     * @return the response entity
     */
    @PutMapping("/{nickname}/{idx}")
    public ResponseEntity updated(
            Comment comment,
            @PathVariable String nickname,
            @PathVariable Long idx
    ) {
        if (comment == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not found required \"comment\" parameter");
        } else if (nickname == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not found \"nickname\" path");
        } else if (idx == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not found \"Content-Identification\" path");
        }

        comment = commentService.findByIdx(idx, nickname);
        if (comment == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not found \"comment\" result");
        } else if (!(comment.getCreatedByEntity().getNickname().equals(nickname))) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Missmatch created by and access user");
        }

        return ResponseEntity.status(HttpStatus.OK).body("Success");
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
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not matched \"created By\" and accessed user");
        } else if (nickname == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not found \"nickname\" path");
        } else if (idx == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not found \"comment idx\" path");
        }

        if (!(commentService.deleteByIdx(idx, nickname))) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not found \"comment\" result");
        }

        return ResponseEntity.status(HttpStatus.OK).body("Success");
    }
}