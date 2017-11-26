package hi.cord.com.log.dynamo.nlp.service;

import hi.cord.com.common.service.rest.CommonRestService;
import hi.cord.com.log.dynamo.nlp.domain.NlpPhrase;

public interface NlpPhraseService extends CommonRestService<NlpPhrase> {
    Object[] getKoreanToken(String text);

    Object[] getKoreanPharase(String text);
}
