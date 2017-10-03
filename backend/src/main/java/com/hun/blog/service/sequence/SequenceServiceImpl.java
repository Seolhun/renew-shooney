package com.hun.blog.service.sequence;

import com.hun.blog.domain.sequence.CustomSequences;
import com.hun.blog.domain.sequence.SequenceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SequenceServiceImpl implements SequenceService {
	private static final Logger LOG = LoggerFactory.getLogger(SequenceServiceImpl.class);
	
    private SequenceRepository sequenceRepository;
    private MongoTemplate mongoTemplate;

    @Autowired
    public SequenceServiceImpl(SequenceRepository sequenceRepository, MongoTemplate mongoTemplate) {
        this.sequenceRepository = sequenceRepository;
        this.mongoTemplate = mongoTemplate;
    }

	@Override
	@Transactional
	public CustomSequences findByKey(String key) {
		Query query = new Query();
		query.with(new Sort(Sort.Direction.DESC, "_id"));
		query.addCriteria(Criteria.where("key").is(key));
//		query.addCriteria(Criteria.where("name").regex("^A"));
//		query.addCriteria(Criteria.where("age").lt(50).gt(20));
//		@Query("{ 'name' : { $regex: ?0 } }")
		
		query.limit(1);
		CustomSequences sequence = mongoTemplate.findOne(query, CustomSequences.class);

		//If null started 1
		if(sequence == null){
			sequence = new CustomSequences();
			sequence.setId(1);
			sequence.setKey(key);
		} else {
			LOG.info("return : sequence {}", sequence.toString());
			sequence.setId(sequence.getId()+1);
		}
		sequenceRepository.save(sequence);
		return sequence;
	}
}
