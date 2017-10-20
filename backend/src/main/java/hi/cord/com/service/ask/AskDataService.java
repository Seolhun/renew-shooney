package hi.cord.com.service.ask;

import hi.cord.com.domain.ask.AskData;
import hi.cord.com.service.CommonRestService;

/**
 *
 */
public interface AskDataService extends CommonRestService<AskData> {
    AskData findByIdx(long idx);

    Thread getAskThread(long index);
}
