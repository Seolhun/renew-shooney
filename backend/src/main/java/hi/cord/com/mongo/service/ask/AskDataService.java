package hi.cord.com.mongo.service.ask;

import hi.cord.com.mongo.domain.ask.AskData;
import hi.cord.com.common.service.rest.CommonRestService;

/**
 *
 */
public interface AskDataService extends CommonRestService<AskData> {
    AskData findByIdx(long idx);

    Thread getAskThread(long index);
}
