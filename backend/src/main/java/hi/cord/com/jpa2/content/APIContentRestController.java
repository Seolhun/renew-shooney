package hi.cord.com.jpa2.content;

import hi.cord.com.common.domain.pagination.Pagination;
import hi.cord.com.common.service.common.CommonService;
import hi.cord.com.jpa2.comment.service.CommentService;
import hi.cord.com.jpa2.content.domain.Content;
import hi.cord.com.jpa2.content.domain.ContentType;
import hi.cord.com.jpa2.content.service.ContentService;
import hi.cord.com.jpa2.file.service.FileDataService;
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
@RequestMapping("/api/{version}")
@RestController
public class APIContentRestController {
    private static final Logger LOG = LoggerFactory.getLogger(APIContentRestController.class);

    /**
     * Find all response entity.
     *
     * @return the response entity
     */
    @GetMapping(value = "/contentTypes")
    public ResponseEntity getContentTypes() {
        return ResponseEntity.status(HttpStatus.OK).body(ContentType.values());
    }
}