package com.hun.blog.service.news;

import com.hun.blog.domain.news.NewsData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NewsDataService{

    void save(NewsData newsData);

    NewsData findById(long id);

    NewsData findByIdx(String idx);

    Page<NewsData> findAll(Pageable pageable);

    long count();

    void update(NewsData newsData);

    void deleteById(long id);

    void deleteByIdx(String id);

    List<NewsData> selectList();
}