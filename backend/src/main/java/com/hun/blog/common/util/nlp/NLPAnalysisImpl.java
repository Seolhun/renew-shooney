package com.hun.blog.common.util.nlp;

import org.openkoreantext.processor.KoreanTokenJava;
import org.openkoreantext.processor.OpenKoreanTextProcessorJava;
import org.openkoreantext.processor.phrase_extractor.KoreanPhraseExtractor;
import org.openkoreantext.processor.tokenizer.KoreanTokenizer;
import scala.collection.Seq;

import java.util.List;

public class NLPAnalysisImpl implements NLPAnalysis {

    @Override
    public List<KoreanTokenJava> getKoreanToken(String text) {
        // Normalize
        CharSequence normalized = OpenKoreanTextProcessorJava.normalize(text);
        // 한국어를 처리하는 예시입니다ㅋㅋ #한국어

        // Tokenize
        Seq<KoreanTokenizer.KoreanToken> tokens = OpenKoreanTextProcessorJava.tokenize(normalized);
        // [한국어, 를, 처리, 하는, 예시, 입니, 다, ㅋㅋ, #한국어]

        return OpenKoreanTextProcessorJava.tokensToJavaKoreanTokenList(tokens);
        // [한국어(Noun: 0, 3), 를(Josa: 3, 1), 처리(Noun: 5, 2), 하는(Verb(하다): 7, 2), 예시(Noun: 10, 2),
        // 입니다(Adjective(이다): 12, 3), ㅋㅋㅋ(KoreanParticle: 15, 3), #한국어(Hashtag: 19, 4)]
    }

    @Override
    public List<KoreanPhraseExtractor.KoreanPhrase> getKoreanPharase(String text) {
        // Normalize
        CharSequence normalized = OpenKoreanTextProcessorJava.normalize(text);
        // Tokenize
        Seq<KoreanTokenizer.KoreanToken> tokens = OpenKoreanTextProcessorJava.tokenize(normalized);
        // Phrase extraction

        return OpenKoreanTextProcessorJava.extractPhrases(tokens, true, true);
        // [한국어(Noun: 0, 3), 처리(Noun: 5, 2), 처리하는 예시(Noun: 5, 7), 예시(Noun: 10, 2), #한국어(Hashtag: 18, 4)]
    }
}
