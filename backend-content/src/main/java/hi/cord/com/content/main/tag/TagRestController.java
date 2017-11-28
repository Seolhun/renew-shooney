package hi.cord.com.content.main.tag;

import hi.cord.com.common.domain.pagination.Pagination;
import hi.cord.com.common.service.CommonService;
import hi.cord.com.content.main.tag.domain.Tag;
import hi.cord.com.content.main.tag.service.TagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * The type Tag rest controller.
 */
@CrossOrigin(origins = {"http://localhost:7000", "http://localhost:5000", "http://127.0.0.1:7000", "http://127.0.0.1:5000"})
@RequestMapping("/tag")
@RestController
public class TagRestController {
    private static final Logger LOG = LoggerFactory.getLogger(TagRestController.class);

    private CommonService commonService;
    private TagService tagService;

    @Autowired
    public TagRestController(CommonService commonService, TagService tagService) {
        this.commonService = commonService;
        this.tagService = tagService;
    }

    /**
     * Find all response entity.
     *
     * @return the response entity
     */
    @GetMapping(value = "")
    public ResponseEntity findAll(
            Tag tag,
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
        Example<Tag> tagExample = Example.of(tag);
        Pagination<Tag> tagPagination = tagService.findAll(tagExample, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(tagPagination);
    }

    /**
     * Save response entity.
     *
     * @return the response entity
     */
    @PostMapping("")
    public ResponseEntity insert(
            @Valid @RequestBody Tag tag
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(tag);
    }


    /**
     * Find one response entity.
     *
     * @return the response entity
     */
    @GetMapping("/{tagName}")
    public ResponseEntity findOne(
            @PathVariable String tagName
    ) {
        if (tagName == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not found Tag name");
        }
        Tag tag = tagService.findByName(tagName);

        if (tag == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not found tag result");
        }
        return ResponseEntity.status(HttpStatus.OK).body(tag);
    }
}