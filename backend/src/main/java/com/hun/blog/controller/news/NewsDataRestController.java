package com.hun.blog.controller.news;

import com.hun.blog.common.domain.Paging;
import com.hun.blog.common.domain.Result;
import com.hun.blog.domain.news.NewsData;
import com.hun.blog.domain.news.NewsWebSite;
import com.hun.blog.service.news.NewsDataService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/news")
public class NewsDataRestController {
    private static final Logger LOG = LoggerFactory.getLogger(NewsDataRestController.class);

    private NewsDataService newsDataService;

    @Autowired
    public NewsDataRestController(NewsDataService newsDataService) {
        this.newsDataService = newsDataService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public Result<NewsData> saveNews(@PathVariable long id) {
        LOG.info("where : saveNews");
        Result<NewsData> result = new Result<>();

        getNewsThread(id).start();

        result.setMessage("success");
        return result;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public NewsData getNewsDetail(@PathVariable long id) {
        return newsDataService.findById(id);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Map<String, Object> getNewsListData() {
        //Paging
        Paging paging = new Paging();

        // 전체 게시판 갯수 확인
        long totalCount = newsDataService.count();
        paging.setTotalCount((int) totalCount);

        PageRequest pageRequest = new PageRequest(paging.getCurrentPage(), paging.getLimit(), Direction.DESC, "NEWS_IDX");
        Page<NewsData> newsDatas = newsDataService.findAll(pageRequest);
        for (NewsData news : newsDatas) {
            LOG.info("return : news {}", news.toString());
        }

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("newsDatas", newsDatas);
        resultMap.put("paging", paging);
        return resultMap;
    }

    @RequestMapping(value = "/stop", method = RequestMethod.GET)
    public Result<NewsData> stopThreadNews(
            Result<NewsData> result) {
        LOG.info("where : stopThreadNews");
        stopNewsThread();
        result.setMessage("success");
        return result;
    }

    private Thread getNewsThread(long startNumber) {
        return new Thread(() -> {
            LOG.info("return : getNewsThread : Message");
//				for (int i = 3100000; i<= 3182689; i++) {
            for (long i = startNumber; i < 3100000; i++) {
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
                    newsData.setId(i);
                    newsData.setIdx(webSiteName + "_" + i);
                    newsData.setTitle(newsTitle);
                    newsData.setCreatedBy(newsWriter);
                    newsData.setFromSource(newsSource);
                    newsData.setHeaderImage(newsHeadImage);
                    newsData.setContent(newsContent);
                    newsData.setTags(tags);

                    newsDataService.save(newsData);
                } catch (NullPointerException e) {
                    LOG.error("ERROR : NullPointerException");
                } catch (IOException e) {
                    LOG.error("ERROR : IOException");
                }
            }
        });
    }

    private void stopNewsThread() {
        // 현재 돌고있는 쓰레드를 객체로 반환.
        Thread thread = Thread.currentThread();
        String name = thread.getName();
        System.out.println("현재 쓰레드 이름 : " + name);
        thread.run();
    }

}
