package hi.cord.com.common.service.rest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CommonRestService<E> {
    void insert(E e);

    List<E> findByList();
    
    Page<E> findByPage(E e, Pageable pageable);

    E findById(String pk);

    void deleteById(String pk);

    void update(E e);

    long count(E e);
}