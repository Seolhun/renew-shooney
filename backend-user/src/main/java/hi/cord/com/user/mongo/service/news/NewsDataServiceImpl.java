package hi.cord.com.user.mongo.service.news;

import hi.cord.com.user.dynamo.service.nlp.NlpPhraseService;
import hi.cord.com.user.mongo.domain.news.NewsData;
import hi.cord.com.user.mongo.domain.news.NewsDataRepository;
import hi.cord.com.user.mongo.domain.news.NewsWebSite;
import hi.cord.com.user.mongo.domain.sequence.CustomSequence;
import hi.cord.com.user.mongo.service.sequence.SequenceService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class NewsDataServiceImpl implements NewsDataService {
    private static final Logger LOG = LoggerFactory.getLogger(NewsDataServiceImpl.class);

    private NewsDataRepository newsDataRepository;
    private SequenceService sequenceService;
    private NlpPhraseService nlpPhraseService;

    @Autowired
    public NewsDataServiceImpl(NewsDataRepository newsDataRepository, SequenceService sequenceService, NlpPhraseService nlpPhraseService) {
        this.newsDataRepository = newsDataRepository;
        this.sequenceService = sequenceService;
        this.nlpPhraseService = nlpPhraseService;
    }

    @Override
    public NewsData insert(NewsData newsData) {
        newsDataRepository.save(newsData);
        return newsData;
    }

    @Override
    public List<NewsData> findByList() {
        return null;
    }

    @Override
    public Page<NewsData> findByPage(NewsData newsData, Pageable pageable) {
        return null;
    }

    @Override
//    @Caching(cacheable = {@Cacheable(key = "'news'+#id", value = "news")})
    public NewsData findById(String id) {
        return newsDataRepository.findById(id);
    }

    @Override
    public NewsData findById(long id) {
        return null;
    }

    @Override
    public boolean deleteById(String id, String accessBy) {
        return false;
    }

    @Override
    public NewsData findByIdx(long idx) {
        return null;
    }

    @Override
    public NewsData updateById(NewsData newsData, String accessBy) {
        NewsData dbNews = this.findById(newsData.getId());
        if (dbNews != null)
            newsData.setModifiedDate(new Date());
        this.insert(newsData);

        return newsData;
    }

    @Override
    public boolean deleteById(long id, String accessBy) {
        return false;
    }

    @Override
    public boolean deleteByIdx(long idx, String accessBy) {
        return false;
    }

    @Override
    public long count(NewsData newsData) {
        return newsDataRepository.count();
    }

    @Override
    public Thread getNewsThread(long id) {
        LOG.debug("p : id = {}", id);
        CustomSequence sequence = new CustomSequence(id, "news");
        Thread thread = new Thread(() -> {
            long i = id;
            boolean isRight = true;
            while (isRight) {
                LOG.debug("p : index : {}", i);
                // 리스트 가져오기
                try {
                    String webSiteName = "";

                    Document doc = null;
                    for (NewsWebSite newsWebSite : NewsWebSite.values()) {
                        String address = newsWebSite.getWebAddress();
                        webSiteName = newsWebSite.name();
                        doc = Jsoup.connect(address + i).timeout(8000).userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.95 Safari/537.36").ignoreHttpErrors(true).get();
                        //하나라도 신문기사가 있으면 포문을 종료시키고 넘어간다.
                        if (doc != null)
                            break;
                    }

                    //모두 돌았는데도 404일때 신문기사가 없으므로 돌아간다.(계속진행 시킨다.)
                    if (doc == null) {
                        continue;
                    }

                    NewsData newsData = new NewsData();
                    String newsTitle = doc.getElementsByTag("title").html();
                    String newsWriter = doc.select("meta[name=author]").attr("content");
                    String newsSource = doc.select("meta[name=source]").attr("content");
                    String newsHeadImage = doc.select("meta[name=parsely-image-url]").attr("content");
                    String newsContent = doc.getElementById("drr-container").html();
                    Elements newsTags = doc.getElementsByClass("tags").select("ul > li >a");

                    List<String> tags = new ArrayList<>();
                    for (Element tagName : newsTags) {
                        String tag = tagName.html();
                        tags.add(tag);
                    }

                    newsData.setId(webSiteName + "_" + i);
                    newsData.setIdx(i);
                    newsData.setTitle(newsTitle);
                    newsData.setCreatedBy(newsWriter);
                    newsData.setFromSource(newsSource);
                    newsData.setHeaderImage(newsHeadImage);
                    newsData.setContent(newsContent);
                    newsData.setTags(tags);

                    sequence.setId(i);
                    sequenceService.save(sequence);

                    newsDataRepository.save(newsData);
                } catch (NullPointerException e) {
                    LOG.error("ERROR : NullPointerException");
                } catch (IOException e) {
                    LOG.error("ERROR : IOException");
                }
                i++;
            }
        });
        return thread;
    }

}
