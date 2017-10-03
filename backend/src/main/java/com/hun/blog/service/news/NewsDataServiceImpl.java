package com.hun.blog.service.news;

import com.hun.blog.domain.news.NewsData;
import com.hun.blog.domain.news.NewsDataRepository;
import com.hun.blog.service.CommonRestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class NewsDataServiceImpl implements CommonRestService<NewsData>, NewsDataService {
    private static final Logger LOG = LoggerFactory.getLogger(NewsDataServiceImpl.class);

    @Autowired
    private NewsDataRepository newsDataRepository;

    @Override
    public void save(NewsData newsData) {
        LOG.info("param : save : {}", newsData.toString());
        newsDataRepository.save(newsData);
    }

    @Override
    public NewsData findById(long id) {
        return newsDataRepository.findById(id);
    }

    @Override
    public NewsData findByIdx(String idx) {
        LOG.info("param : findByIdx : {}", idx);
        return newsDataRepository.findOne(idx);
    }

    @Override
    @Caching(cacheable = {@Cacheable(key = "#pageable+'findNewsList'", value = "findNewsList")})
    public Page<NewsData> findAll(Pageable pageable) {
        LOG.info("param : findAll : {}", pageable.toString());
        return newsDataRepository.findAll(pageable);
    }

    @Override
    public long count() {
        return newsDataRepository.count();
    }

    @Override
    public void update(NewsData newsData) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteById(long id) {

    }

    @Override
    public void deleteByIdx(String id) {

    }

    @Override
    public List<NewsData> selectList() {
        return newsDataRepository.findAll();
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
    public NewsData findOneById(String id) {
        return null;
    }

    @Override
    public void insert(NewsData newsData) {

    }

    @Override
    public void updateById(String id) {

    }

    @Override
    public void deleteById(String id) {

    }

    @Override
    public long count(NewsData newsData) {
        return 0;
    }
}
