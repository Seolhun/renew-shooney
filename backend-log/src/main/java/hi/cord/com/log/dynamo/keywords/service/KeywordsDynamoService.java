package hi.cord.com.log.dynamo.keywords.service;

import hi.cord.com.log.dynamo.keywords.domain.Keywords;

public interface KeywordsDynamoService {
	void saveKeywords(Keywords keywords);

	Keywords findById(String id);
}
