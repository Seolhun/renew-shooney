package hi.cord.com.user.dynamo.service.keywords;

import hi.cord.com.user.dynamo.domain.keywords.Keywords;

public interface KeywordsDynamoService {
	void saveKeywords(Keywords keywords);

	Keywords findById(String id);
}
