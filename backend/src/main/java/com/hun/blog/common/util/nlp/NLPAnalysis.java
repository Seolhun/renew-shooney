package com.hun.blog.common.util.nlp;

import org.openkoreantext.processor.KoreanTokenJava;
import org.openkoreantext.processor.phrase_extractor.KoreanPhraseExtractor;

import java.util.List;

public interface NLPAnalysis {
    List<KoreanTokenJava> getKoreanToken(String text);

    List<KoreanPhraseExtractor.KoreanPhrase> getKoreanPharase(String text);
}
