package hi.cord.com.jpa2.board.service;

import hi.cord.com.common.service.rest.CommonRestService;
import hi.cord.com.jpa2.board.domain.Board;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation=Propagation.REQUIRED, transactionManager="shunTransactionManager", noRollbackFor={NullPointerException.class})
public interface BoardService extends CommonRestService<Board>{

}
