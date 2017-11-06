package hi.cord.com.jpa2.spam;

import hi.cord.com.common.domain.pagination.Pagination;
import hi.cord.com.common.service.common.CommonService;
import hi.cord.com.jpa2.spam.domain.Spam;
import hi.cord.com.jpa2.spam.service.SpamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;


/**
 * The type Spam rest controller.
 */
@RequestMapping("/spam")
@RestController
public class SpamRestController {
    private static final Logger LOG = LoggerFactory.getLogger(SpamRestController.class);

    private CommonService commonService;
    private SpamService spamService;

    @Autowired
    public SpamRestController(CommonService commonService, SpamService spamService) {
        this.commonService = commonService;
        this.spamService = spamService;
    }

    /**
     * Find all response entity.
     *
     * @return the response entity
     */
    @GetMapping(value = "")
    public ResponseEntity findAll(
            Spam spam,
            @RequestParam(required = false) Integer pageIndex,
            @RequestParam(required = false) Integer pageSize
    ) {
        //Pagination and FindAll
        Pageable pageable = commonService.getPageable(pageIndex, pageSize);
        Pagination<Spam> spamPagination = spamService.findAll(spam, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(spamPagination);
    }

    /**
     * Save response entity.
     *
     * @return the response entity
     */
    @PostMapping("")
    public ResponseEntity insert(
            @Valid @RequestBody Spam spam,
            Errors errors
    ) {
        if (spam == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not found \"spam\" parameter");
        } else if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getFieldError().getDefaultMessage());
        }

        // Insert
        spamService.insert(spam);
        LOG.debug("p : save spam {}", spam.toString());
        return ResponseEntity.status(HttpStatus.OK).body("Success");
    }


    /**
     * Find one response entity.
     *
     * @return the response entity
     */
    @GetMapping("/{nickname}/{id}")
    public ResponseEntity findOne(
            @PathVariable String nickname,
            @PathVariable Long id
    ) {
        if (nickname == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not found \"nickname\" path");
        } else if (id == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not found \"Spam-Identification\" path");
        }

        Spam spam = spamService.findById(id);
        if (spam == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not found \"spam\" result");
        } else if (!(spam.getCreatedByEntity().getNickname().equals(nickname))) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Missmatch created by and access user");
        }

        return ResponseEntity.status(HttpStatus.OK).body(spam);
    }

    /**
     * Updated response entity.
     *
     * @return the response entity
     */
    @PutMapping("/{nickname}/{id}")
    public ResponseEntity updated(
            Spam spam,
            @PathVariable String nickname,
            @PathVariable Long id
    ) {
        if (spam == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not found required \"spam\" parameter");
        } else if (nickname == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not found \"nickname\" path");
        } else if (id == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not found \"Spam-Identification\" path");
        }

        spam = spamService.findById(id);
        if (spam == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not found \"spam\" result");
        } else if (!(spam.getCreatedByEntity().getNickname().equals(nickname))) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Missmatch created by and access user");
        }

        return ResponseEntity.status(HttpStatus.OK).body("Success");
    }

    /**
     * Delete response entity.
     *
     * @return the response entity
     */
    @DeleteMapping("/{nickname}/{id}")
    public ResponseEntity delete(
            @PathVariable String nickname,
            @PathVariable Long id,
            Principal principal
    ) {
        if (principal.getName().equals(nickname)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not matched \"created By\" and accessed user");
        } else if (nickname == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not found \"nickname\" path");
        } else if (id == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not found \"Spam-Identification\" path");
        }

        if (!(spamService.deleteByIdx(id, nickname))) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not found \"spam\" result");
        }

        return ResponseEntity.status(HttpStatus.OK).body("Success");
    }
}