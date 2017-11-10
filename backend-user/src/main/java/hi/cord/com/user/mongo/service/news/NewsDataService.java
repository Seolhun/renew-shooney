package hi.cord.com.user.mongo.service.news;

import hi.cord.com.user.mongo.domain.news.NewsData;
import hi.cord.com.user.common.service.abs.AbstractRestService;

public interface NewsDataService extends AbstractRestService<NewsData> {
    NewsData findByIdx(long idx);

    Thread getNewsThread(long index);
}