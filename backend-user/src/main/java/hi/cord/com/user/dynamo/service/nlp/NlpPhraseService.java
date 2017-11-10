package hi.cord.com.user.dynamo.service.nlp;

import hi.cord.com.user.common.service.abs.AbstractRestService;
import hi.cord.com.user.dynamo.domain.nlp.NlpPhrase;

public interface NlpPhraseService extends AbstractRestService<NlpPhrase> {
    Object[] getKoreanToken(String text);

    Object[] getKoreanPharase(String text);
}
