package hi.cord.com.common.service.rest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AbstractRestService<E> {
    E insert(E e);

    List<E> findByList();
    
    Page<E> findByPage(E e, Pageable pageable);

    E findById(String id);

    // If you use String id, add long idx
    E findByIdx(long idx);

    E findById(long id);

    boolean deleteById(String id);

    boolean deleteById(long id);

    boolean deleteByIdx(long idx);

    E updateById(E e);

    long count(E e);
}