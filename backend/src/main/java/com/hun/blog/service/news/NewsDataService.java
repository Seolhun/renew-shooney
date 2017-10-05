package com.hun.blog.service.news;

import com.hun.blog.domain.news.NewsData;
import com.hun.blog.service.CommonRestService;

public interface NewsDataService extends CommonRestService<NewsData> {
    NewsData findByIdx(long idx);

    Thread getNewsThread(long index);
}