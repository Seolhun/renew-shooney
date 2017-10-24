package hi.cord.com.dynamo.service.keywords;

import hi.cord.com.dynamo.domain.keywords.Keywords;

public interface KeywordsDynamoService {
	void saveKeywords(Keywords keywords);

	Keywords findById(String id);
}
