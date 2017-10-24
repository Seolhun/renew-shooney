package hi.cord.com.dynamo.service.nlp;

import hi.cord.com.dynamo.domain.nlp.NlpPhrase;
import hi.cord.com.dynamo.domain.nlp.NlpPhraseRepository;
import org.openkoreantext.processor.KoreanTokenJava;
import org.openkoreantext.processor.OpenKoreanTextProcessorJava;
import org.openkoreantext.processor.phrase_extractor.KoreanPhraseExtractor;
import org.openkoreantext.processor.tokenizer.KoreanTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import scala.collection.Seq;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class NlpPhraseServiceImpl implements NlpPhraseService {

    private NlpPhraseRepository nlpPhraseRepository;

    @Autowired
    public NlpPhraseServiceImpl(NlpPhraseRepository nlpPhraseRepository) {
        this.nlpPhraseRepository = nlpPhraseRepository;
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
    public void insert(NlpPhrase nlpPhrase) {
        nlpPhrase.setCreatedDate(new Date());
        nlpPhraseRepository.insert(nlpPhrase);
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
    public void deleteById(String id) {

    }

    @Override
    public void update(NlpPhrase nlpPhrase) {

    }

    @Override
    public long count(NlpPhrase nlpPhrase) {
        return 0;
    }
}
