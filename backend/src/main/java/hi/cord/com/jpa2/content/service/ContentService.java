package hi.cord.com.jpa2.content.service;

import hi.cord.com.common.domain.pagination.Pagination;
import hi.cord.com.common.service.abs.AbstractRestService;
import hi.cord.com.jpa2.content.domain.Content;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ContentService extends AbstractRestService<Content> {
    long findByCreatedByEntityNickname(String nickname);

    Pagination<Content> findAll(Content content, Pageable pageable);

    Content findByIdx(long idx, String nickname);
}
