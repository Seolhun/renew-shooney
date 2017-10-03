package com.hun.blog.domain.ask;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AskDataRepository extends MongoRepository<AskData, String> {

    AskData findById(long id);

	long count();
	
    List<AskData> findAll();
    
    Page<AskData> findAll(Pageable pageable);
}
