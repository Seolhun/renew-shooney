package com.hun.blog.service.ask;

import com.hun.blog.domain.ask.AskData;
import com.hun.blog.domain.ask.AskDataRepository;
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
public class AskDataServiceImpl implements CommonRestService<AskData>, AskDataService {
    private static final Logger LOG = LoggerFactory.getLogger(AskDataServiceImpl.class);

    @Autowired
    private AskDataRepository askDataRepository;


    @Override
    public List<AskData> findByList() {
        return null;
    }

    @Override
    public Page<AskData> findByPage(AskData askData, Pageable pageable) {
        return null;
    }

    @Override
    public AskData findOneById(String id) {
        return null;
    }

    @Override
    public void insert(AskData askData) {

    }

    @Override
    public void updateById(String id) {

    }

    @Override
    public void deleteById(String id) {

    }

    @Override
    public long count(AskData askData) {
        return 0;
    }
}
