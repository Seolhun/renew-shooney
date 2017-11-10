package hi.cord.com.content.content.service;

import hi.cord.com.common.domain.pagination.Pagination;
import hi.cord.com.common.service.abs.AbstractRestService;
import hi.cord.com.content.content.domain.BlogContent;
import org.springframework.data.domain.Pageable;

public interface BlogContentService extends AbstractRestService<BlogContent> {
    long getIdxByNickname(String nickname);

    Pagination<BlogContent> findAll(BlogContent blogContent, Pageable pageable);

    BlogContent findByIdx(long idx, String nickname);
}
