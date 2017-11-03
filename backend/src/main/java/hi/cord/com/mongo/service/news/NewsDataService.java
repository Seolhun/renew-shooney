package hi.cord.com.mongo.service.news;

import hi.cord.com.mongo.domain.news.NewsData;
import hi.cord.com.common.service.abs.AbstractRestService;

public interface NewsDataService extends AbstractRestService<NewsData> {
    NewsData findByIdx(long idx);

    Thread getNewsThread(long index);
}