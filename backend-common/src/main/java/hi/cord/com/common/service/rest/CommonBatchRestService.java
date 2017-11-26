package hi.cord.com.common.service.rest;

import java.util.List;

public interface CommonBatchRestService<E> {
    List<E> insertBatchByList(List<E> list);

}