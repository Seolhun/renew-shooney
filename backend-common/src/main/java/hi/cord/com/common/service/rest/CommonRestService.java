package hi.cord.com.common.service.rest;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.List;

public interface CommonRestService<E> {
    E insert(E e) throws FileUploadException, IOException;

    List<E> findByList();
    
    Page<E> findByPage(E e, Pageable pageable);

    E findById(String id);

    // If you use String id, add long idx
    E findByIdx(long idx);

    E findById(long id);

    boolean deleteById(String id, String accessBy);

    boolean deleteById(long id, String accessBy);

    boolean deleteByIdx(long idx, String accessBy);

    E updateById(E e, String accessBy);

    long count(E e);
}