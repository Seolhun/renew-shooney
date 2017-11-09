package hi.cord.com.jpa2.content.service;

import hi.cord.com.common.domain.pagination.Pagination;
import hi.cord.com.common.service.abs.AbstractRestService;
import hi.cord.com.jpa2.content.domain.BlogContent;
import org.springframework.data.domain.Pageable;

public interface BlogContentService extends AbstractRestService<BlogContent> {
    long getIdxByNickname(String nickname);

    Pagination<BlogContent> findAll(BlogContent blogContent, Pageable pageable);

    BlogContent findByIdx(long idx, String nickname);
}
