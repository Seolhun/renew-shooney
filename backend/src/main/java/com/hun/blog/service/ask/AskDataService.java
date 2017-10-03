package com.hun.blog.service.ask;

import com.hun.blog.domain.ask.AskData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 *
 */
public interface AskDataService {
    List<AskData> findByList();

    Page<AskData> findByPage(AskData askData, Pageable pageable);

    AskData findOneById(String id);

    void insert(AskData askData);

    void updateById(String id);

    void deleteById(String id);

    long count(AskData askData);
}