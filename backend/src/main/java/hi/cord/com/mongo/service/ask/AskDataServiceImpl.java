package hi.cord.com.mongo.service.ask;

import hi.cord.com.common.service.CommonService;
import hi.cord.com.dynamo.domain.nlp.NlpPhrase;
import hi.cord.com.dynamo.service.nlp.NlpPhraseService;
import hi.cord.com.mongo.domain.ask.AskData;
import hi.cord.com.mongo.domain.ask.AskDataRepository;
import hi.cord.com.mongo.domain.ask.AskWebSite;
import hi.cord.com.mongo.domain.sequence.CustomSequence;
import hi.cord.com.mongo.service.sequence.SequenceService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class AskDataServiceImpl implements AskDataService {
    private static final Logger LOG = LoggerFactory.getLogger(AskDataServiceImpl.class);

    private SequenceService sequenceService;
    private NlpPhraseService nlpPhraseService;
    private CommonService commonService;

    private AskDataRepository askDataRepository;

    @Autowired
    public AskDataServiceImpl(SequenceService sequenceService, NlpPhraseService nlpPhraseService, CommonService commonService, AskDataRepository askDataRepository) {
        this.sequenceService = sequenceService;
        this.nlpPhraseService = nlpPhraseService;
        this.commonService = commonService;
        this.askDataRepository = askDataRepository;
    }

    @Override
    public List<AskData> findByList() {
        return null;
    }

    @Override
    public Page<AskData> findByPage(AskData askData, Pageable pageable) {
        return askDataRepository.findAll(pageable);
    }

    @Override
    public AskData findById(String id) {
        return askDataRepository.findById(id);
    }

    @Override
    public AskData findById(long id) {
        return null;
    }

    @Override
    public boolean deleteById(String id) {
        return false;
    }

    @Override
    public boolean deleteById(long id) {
        return false;
    }

    @Override
    public AskData findByIdx(long idx) {
        return askDataRepository.findByIdx(idx);
    }

    @Override
    public AskData insert(AskData askData) {
        askData.setCreatedDate(new Date());
        askDataRepository.insert(askData);
        return askData;
    }

    @Override
    public AskData update(AskData askData) {
        AskData dbAsk = this.findById(askData.getId());
        if (dbAsk != null)
            askData.setModifiedDate(new Date());
        this.insert(askData);
        return askData;
    }

    @Override
    public long count(AskData askData) {
        return 0;
    }

    @Override
    public Thread getAskThread(long id) {
        CustomSequence sequence = new CustomSequence(id, "ask");
        return new Thread(() -> {
            long i = id;
            int errorCount = 0;

            boolean isRight = true;
            while (isRight) {
                LOG.info("param index : {}", i);
                // 리스트 가져오기
                String webSiteName = "okky";
                String address = AskWebSite.OKKY.getAddress();

                Document doc;
                try {
                    doc = Jsoup.connect(address + i).timeout(8000).userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.95 Safari/537.36").ignoreHttpErrors(true).get();
                    AskData askData = new AskData();
                    String title = doc.getElementById("content-body").getElementsByClass("panel-title").html();
                    String content = doc.getElementById("content-body").getElementsByClass("content-text").html();
                    Object[] list = nlpPhraseService.getKoreanPharase(content);
                    for (Object o : list) {
                        NlpPhrase nlpPhrase = new NlpPhrase(address + i);
                        nlpPhrase.setPhrase(o.toString());
                        nlpPhraseService.insert(nlpPhrase);
                    }

                    content = commonService.removeTags(content);
                    Elements elTags = doc.getElementsByClass("content-tags").select("a.item-tag");
                    String writer = doc.getElementsByClass("panel-heading").select("div > .avatar-info > a.nickname").html();

                    List<String> tags = new ArrayList<>();
                    for (Element tag : elTags) {
                        tags.add(commonService.removeTags(tag.html()));
                    }

                    askData.setId(webSiteName + "_" + i);
                    askData.setIdx(i);
                    askData.setTitle(title);
                    askData.setContent(content);
                    askData.setCreatedBy(writer);
                    askData.setTags(tags);

                    if (askDataRepository.findById(askData.getId()) == null) {
                        sequence.setId(i);
                        sequenceService.save(sequence);
                        askDataRepository.insert(askData);
                        errorCount = 0;
                    }
                } catch (Exception e) {
                    errorCount++;
                    //Continuous 10 happen error, Stop Thread
                    if (errorCount > 50) {
                        LOG.info("test End");
                        isRight = false;
                    }
                }
                i++;
            }
        });
    }
}
