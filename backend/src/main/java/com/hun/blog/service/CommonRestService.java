package com.hun.blog.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommonRestService<E> {
    void insert(E e);

    List<E> findByList();
    
    Page<E> findByPage(E e, Pageable pageable);

    E findById(String id);

    void deleteById(String id);

    void update(E e);

    long count(E e);
}