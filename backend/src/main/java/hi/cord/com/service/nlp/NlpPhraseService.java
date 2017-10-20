package hi.cord.com.service.nlp;

import hi.cord.com.domain.nlp.NlpPhrase;
import hi.cord.com.service.CommonRestService;

public interface NlpPhraseService extends CommonRestService<NlpPhrase> {
    Object[] getKoreanToken(String text);

    Object[] getKoreanPharase(String text);
}
