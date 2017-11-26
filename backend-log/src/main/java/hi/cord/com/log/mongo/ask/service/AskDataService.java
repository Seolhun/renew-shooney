package hi.cord.com.log.mongo.ask.service;

import hi.cord.com.common.service.rest.CommonRestService;
import hi.cord.com.log.mongo.ask.domain.AskData;

/**
 *
 */
public interface AskDataService extends CommonRestService<AskData> {
    AskData findByIdx(long idx);

    Thread getAskThread(long index);
}
