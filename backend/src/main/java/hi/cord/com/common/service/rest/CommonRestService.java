package hi.cord.com.common.service.rest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CommonRestService<E> {
    E insert(E e);

    List<E> findByList();
    
    Page<E> findByPage(E e, Pageable pageable);

    E findById(String id);

    E findById(long id);

    boolean deleteById(String id);

    boolean deleteById(long id);

    E update(E e);

    long count(E e);
}