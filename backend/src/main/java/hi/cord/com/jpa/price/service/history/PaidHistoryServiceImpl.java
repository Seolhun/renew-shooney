package hi.cord.com.jpa.price.service.history;

import hi.cord.com.jpa.price.domain.history.PaidHistory;
import hi.cord.com.jpa.price.domain.history.PaidHistoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("priceRecordService")
public class PaidHistoryServiceImpl implements PaidHistoryService {
    private static final Logger LOG = LoggerFactory.getLogger(PaidHistoryServiceImpl.class);

    private PaidHistoryRepository paidHistoryRepository;

    @Autowired
    public PaidHistoryServiceImpl(PaidHistoryRepository paidHistoryRepository) {
        this.paidHistoryRepository = paidHistoryRepository;
    }

    @Override
    public PaidHistory findById(long id) {
        return paidHistoryRepository.findById(id);
    }


    @Override
    public PaidHistory findById(String id) {
        return null;
    }

    @Override
    public boolean deleteById(String id) {
        return false;
    }

    @Override
    public boolean deleteById(long id) {
        return false;
    }

    @Override
    public PaidHistory insert(PaidHistory paidHistory) {
        return paidHistoryRepository.save(paidHistory);
    }

    @Override
    public List<PaidHistory> findByList() {
        return null;
    }

    @Override
    public Page<PaidHistory> findByPage(PaidHistory paidHistory, Pageable pageable) {
        return null;
    }


    @Override
    public PaidHistory update(PaidHistory paidHistory) {
        PaidHistory dbPaidHistory = paidHistoryRepository.findById(paidHistory.getId());
        return paidHistory;
    }

    @Override
    public long count(PaidHistory paidHistory) {
        return 0;
    }
}