package com.hun.blog.service.ask;

import com.hun.blog.domain.ask.AskData;
import com.hun.blog.service.CommonRestService;

/**
 *
 */
public interface AskDataService extends CommonRestService<AskData> {
    AskData findByIdx(long idx);

    Thread getAskThread(long index);
}
