package hi.cord.com.log.mongo.news.service;

import hi.cord.com.common.service.abs.AbstractRestService;
import hi.cord.com.log.mongo.news.domain.NewsData;

public interface NewsDataService extends AbstractRestService<NewsData> {
    NewsData findByIdx(long idx);

    Thread getNewsThread(long index);
}