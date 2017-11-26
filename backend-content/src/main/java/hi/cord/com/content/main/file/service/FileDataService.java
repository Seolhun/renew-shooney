package hi.cord.com.content.main.file.service;

import hi.cord.com.common.service.rest.CommonRestService;
import hi.cord.com.content.main.file.domain.FileData;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation= Propagation.REQUIRED, transactionManager="shunTransactionManager", noRollbackFor={NullPointerException.class})
public interface FileDataService extends CommonRestService<FileData> {

}
