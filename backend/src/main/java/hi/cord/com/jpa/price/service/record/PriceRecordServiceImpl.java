package hi.cord.com.jpa.price.service.record;

import hi.cord.com.jpa.price.domain.PriceRecord;
import hi.cord.com.jpa.price.repository.PriceRecordRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("priceRecordService")
@Transactional
public class PriceRecordServiceImpl implements PriceRecordService {
	private static final Logger LOG = LoggerFactory.getLogger(PriceRecordServiceImpl.class);

	private PriceRecordRepository priceRecordRepository;

	@Autowired
	public PriceRecordServiceImpl(PriceRecordRepository priceRecordRepository) {
		this.priceRecordRepository = priceRecordRepository;
	}

	@Override
	public PriceRecord findById(long id) {
		return priceRecordRepository.findById(id);
	}

	@Override
	public void insert(PriceRecord priceRecord) {
		priceRecordRepository.insert(priceRecord);
	}

	@Override
	public PriceRecord update(PriceRecord priceRecord) {
		PriceRecord dbPriceRecord=priceRecordRepository.findById(priceRecord.getId());
		return priceRecord;
	}

	@Override
	public void deleteById(long id) {
		PriceRecord priceRecord = priceRecordRepository.findById(id);
	}

	@Override
	public List<PriceRecord> findAllBy(PriceRecord priceRecord) {
		return priceRecordRepository.findAllBy(priceRecord);
	}
}