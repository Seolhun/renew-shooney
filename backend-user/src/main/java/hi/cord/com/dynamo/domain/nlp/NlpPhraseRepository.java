//package hi.cord.com.dynamo.domain.nlp;
//
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.mongodb.repository.MongoRepository;
//import org.springframework.stereotype.Repository;
//
//@Repository
//public interface NlpPhraseRepository extends MongoRepository<NlpPhrase, String> {
//
//    NlpPhrase findById(String id);
//
//	long count();
//
//    Page<NlpPhrase> findAll(Pageable pageable);
//}