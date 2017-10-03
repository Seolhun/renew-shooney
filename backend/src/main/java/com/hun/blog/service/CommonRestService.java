package com.hun.blog.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommonRestService<E> {
    List<E> findByList();
    
    Page<E> findByPage(E e, Pageable pageable);

    E findOneById(String id);

    void insert(E e);

    void updateById(String id);

    void deleteById(String id);

    long count(E e);
}