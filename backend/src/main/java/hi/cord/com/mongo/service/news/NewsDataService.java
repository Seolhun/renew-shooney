package hi.cord.com.mongo.service.news;

import hi.cord.com.mongo.domain.news.NewsData;
import hi.cord.com.common.service.rest.CommonRestService;

public interface NewsDataService extends CommonRestService<NewsData> {
    NewsData findByIdx(long idx);

    Thread getNewsThread(long index);
}