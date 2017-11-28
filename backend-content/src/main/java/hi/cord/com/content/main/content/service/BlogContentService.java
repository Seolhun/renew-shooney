package hi.cord.com.content.main.content.service;

import hi.cord.com.common.domain.pagination.Pagination;
import hi.cord.com.common.service.rest.CommonRestService;
import hi.cord.com.content.main.content.domain.BlogContent;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Pageable;

public interface BlogContentService extends CommonRestService<BlogContent> {
    long getIdxByNickname(String nickname);

    Pagination<BlogContent> findAll(Example<BlogContent> blogContentExample, Pageable pageable);

    BlogContent findByIdx(long idx, String nickname);
}
