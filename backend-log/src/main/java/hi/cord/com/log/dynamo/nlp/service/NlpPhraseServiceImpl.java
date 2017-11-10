package hi.cord.com.log.dynamo.nlp.service;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import hi.cord.com.log.dynamo.nlp.domain.NlpPhrase;
import org.openkoreantext.processor.KoreanTokenJava;
import org.openkoreantext.processor.OpenKoreanTextProcessorJava;
import org.openkoreantext.processor.phrase_extractor.KoreanPhraseExtractor;
import org.openkoreantext.processor.tokenizer.KoreanTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import scala.collection.Seq;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.List;

@Service
public class NlpPhraseServiceImpl implements NlpPhraseService {

    @Value("${aws.dynamo.accesskey}")
    private String accesskey;
    @Value("${aws.dynamo.secretkey}")
    private String secretkey;

    private DynamoDBMapper mapper;

    @PostConstruct
    public void init() {
        if (mapper == null) {
            BasicAWSCredentials basicAWSCredentials = new BasicAWSCredentials(accesskey, secretkey);
            AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
                    .withRegion(Regions.AP_NORTHEAST_2)
                    .withCredentials(new AWSStaticCredentialsProvider(basicAWSCredentials))
                    .build();

            this.mapper = new DynamoDBMapper(client);
        }
    }

    @Override
    public Object[] getKoreanToken(String text) {
        // Normalize
        CharSequence normalized = OpenKoreanTextProcessorJava.normalize(text);
        // 한국어를 처리하는 예시입니다ㅋㅋ #한국어

        // Tokenize
        Seq<KoreanTokenizer.KoreanToken> tokens = OpenKoreanTextProcessorJava.tokenize(normalized);
        // [한국어, 를, 처리, 하는, 예시, 입니, 다, ㅋㅋ, #한국어]
        List<KoreanTokenJava> lists = OpenKoreanTextProcessorJava.tokensToJavaKoreanTokenList(tokens);

        return lists.toArray();
        // [한국어(Noun: 0, 3), 를(Josa: 3, 1), 처리(Noun: 5, 2), 하는(Verb(하다): 7, 2), 예시(Noun: 10, 2),
        // 입니다(Adjective(이다): 12, 3), ㅋㅋㅋ(KoreanParticle: 15, 3), #한국어(Hashtag: 19, 4)]
    }

    @Override
    public Object[] getKoreanPharase(String text) {
        // Normalize
        CharSequence normalized = OpenKoreanTextProcessorJava.normalize(text);
        // Tokenize
        Seq<KoreanTokenizer.KoreanToken> tokens = OpenKoreanTextProcessorJava.tokenize(normalized);
        // Phrase extraction

        List<KoreanPhraseExtractor.KoreanPhrase> phrases = OpenKoreanTextProcessorJava.extractPhrases(tokens, true, true);
        return phrases.toArray();
    }

    @Override
    public NlpPhrase insert(NlpPhrase nlpPhrase) {
        nlpPhrase.setCreatedDate(new Date());
        this.mapper.save(nlpPhrase);
        return nlpPhrase;
    }

    @Override
    public List<NlpPhrase> findByList() {
        return null;
    }

    @Override
    public Page<NlpPhrase> findByPage(NlpPhrase nlpPhrase, Pageable pageable) {
        return null;
    }

    @Override
    public NlpPhrase findById(String id) {
        return null;
    }

    @Override
    public NlpPhrase findById(long id) {
        return null;
    }

    @Override
    public NlpPhrase findByIdx(long idx) {
        return null;
    }

    @Override
    public boolean deleteById(String id, String accessBy) {
        return false;
    }

    @Override
    public boolean deleteById(long id, String accessBy) {
        return false;
    }

    @Override
    public boolean deleteByIdx(long idx, String accessBy) {
        return false;
    }

    @Override
    public NlpPhrase updateById(NlpPhrase nlpPhrase, String accessBy) {
        return nlpPhrase;
    }

    @Override
    public long count(NlpPhrase nlpPhrase) {
        return 0;
    }
}
