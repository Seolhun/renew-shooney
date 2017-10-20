package hi.cord.com.service.news;

import hi.cord.com.domain.news.NewsData;
import hi.cord.com.service.CommonRestService;

public interface NewsDataService extends CommonRestService<NewsData> {
    NewsData findByIdx(long idx);

    Thread getNewsThread(long index);
}