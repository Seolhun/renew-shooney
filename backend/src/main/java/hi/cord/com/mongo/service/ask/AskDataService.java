package hi.cord.com.mongo.service.ask;

import hi.cord.com.mongo.domain.ask.AskData;
import hi.cord.com.common.service.rest.AbstractRestService;

/**
 *
 */
public interface AskDataService extends AbstractRestService<AskData> {
    AskData findByIdx(long idx);

    Thread getAskThread(long index);
}
