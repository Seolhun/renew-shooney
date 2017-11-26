package hi.cord.com.log.mongo.news.service;

import hi.cord.com.common.service.rest.CommonRestService;
import hi.cord.com.log.mongo.news.domain.NewsData;

public interface NewsDataService extends CommonRestService<NewsData> {
    NewsData findByIdx(long idx);

    Thread getNewsThread(long index);
}