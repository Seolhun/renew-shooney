package hi.cord.com.dynamo.service.nlp;

import hi.cord.com.common.service.rest.CommonRestService;
import hi.cord.com.dynamo.domain.nlp.NlpPhrase;

public interface NlpPhraseService extends CommonRestService<NlpPhrase> {
    Object[] getKoreanToken(String text);

    Object[] getKoreanPharase(String text);
}
