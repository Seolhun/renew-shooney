package hi.cord.com.user.common.content.service;

import hi.cord.com.user.common.domain.pagination.Pagination;
import hi.cord.com.user.common.service.abs.AbstractRestService;
import hi.cord.com.user.common.content.domain.BlogContent;
import org.springframework.data.domain.Pageable;

public interface BlogContentService extends AbstractRestService<BlogContent> {
    long getIdxByNickname(String nickname);

    Pagination<BlogContent> findAll(BlogContent blogContent, Pageable pageable);

    BlogContent findByIdx(long idx, String nickname);
}
