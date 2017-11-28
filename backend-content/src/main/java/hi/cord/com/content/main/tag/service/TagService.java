package hi.cord.com.content.main.tag.service;

import hi.cord.com.common.domain.pagination.Pagination;
import hi.cord.com.common.service.rest.CommonRestService;
import hi.cord.com.content.main.tag.domain.Tag;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(propagation = Propagation.REQUIRED, transactionManager = "shunTransactionManager", noRollbackFor = {NullPointerException.class})
public interface TagService extends CommonRestService<Tag> {

    <S extends Tag> List<S> saveIterable(Iterable<S> tags);

    Tag findByName(String tagName);

    Pagination<Tag> findAll(Example<Tag> tagExample, Pageable pageable);
}