package com.hun.blog.service.news;

import com.hun.blog.domain.news.NewsData;
import com.hun.blog.service.CommonRestService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NewsDataService extends CommonRestService<NewsData> {
    Thread getNewsThread(long index);
}