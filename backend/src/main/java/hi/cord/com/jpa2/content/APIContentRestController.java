package hi.cord.com.jpa2.content;

import hi.cord.com.jpa2.content.domain.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * The type BlogContent rest controller.
 */
@RequestMapping("/api/{version}")
@RestController
public class APIContentRestController {
    private static final Logger LOG = LoggerFactory.getLogger(APIContentRestController.class);

    /**
     * Find all response entity.
     *
     * @return the response entity
     */
    @GetMapping(value = "/contentType")
    public ResponseEntity getContentTypes(@PathVariable String version) {
        LOG.info("p : version {}", version);
        return ResponseEntity.status(HttpStatus.OK).body(ContentType.values());
    }
}