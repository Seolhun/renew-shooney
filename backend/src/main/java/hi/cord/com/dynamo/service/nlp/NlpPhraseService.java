package hi.cord.com.dynamo.service.nlp;

import hi.cord.com.common.service.rest.AbstractRestService;
import hi.cord.com.dynamo.domain.nlp.NlpPhrase;

public interface NlpPhraseService extends AbstractRestService<NlpPhrase> {
    Object[] getKoreanToken(String text);

    Object[] getKoreanPharase(String text);
}
