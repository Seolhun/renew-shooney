package hi.cord.com.log.dynamo.nlp.service;

import hi.cord.com.common.service.abs.AbstractRestService;
import hi.cord.com.log.dynamo.nlp.domain.NlpPhrase;

public interface NlpPhraseService extends AbstractRestService<NlpPhrase> {
    Object[] getKoreanToken(String text);

    Object[] getKoreanPharase(String text);
}
