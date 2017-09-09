package com.hun.blog.domain.news;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsDataRepository extends MongoRepository<NewsData, String> {

	NewsData findById(long id);

	long count();
	
    List<NewsData> findAll();
    
    Page<NewsData> findAll(Pageable pageable);
}
