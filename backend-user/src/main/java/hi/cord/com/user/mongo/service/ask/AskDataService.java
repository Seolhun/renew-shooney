package hi.cord.com.user.mongo.service.ask;

import hi.cord.com.user.mongo.domain.ask.AskData;
import hi.cord.com.user.common.service.abs.AbstractRestService;

/**
 *
 */
public interface AskDataService extends AbstractRestService<AskData> {
    AskData findByIdx(long idx);

    Thread getAskThread(long index);
}
