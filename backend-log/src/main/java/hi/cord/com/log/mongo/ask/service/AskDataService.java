package hi.cord.com.log.mongo.ask.service;

import hi.cord.com.common.service.abs.AbstractRestService;
import hi.cord.com.log.mongo.ask.domain.AskData;

/**
 *
 */
public interface AskDataService extends AbstractRestService<AskData> {
    AskData findByIdx(long idx);

    Thread getAskThread(long index);
}
