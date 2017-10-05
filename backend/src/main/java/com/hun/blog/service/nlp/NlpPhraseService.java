package com.hun.blog.service.nlp;

import com.hun.blog.domain.nlp.NlpPhrase;
import com.hun.blog.service.CommonRestService;

public interface NlpPhraseService extends CommonRestService<NlpPhrase> {
    Object[] getKoreanToken(String text);

    Object[] getKoreanPharase(String text);
}
