package hi.cord.com.jpa.price.service.price;

import hi.cord.com.jpa.price.domain.price.Price;
import hi.cord.com.jpa.price.domain.price.PriceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("priceService")
public class PriceServiceImpl implements PriceService {
	private static final Logger LOG = LoggerFactory.getLogger(PriceServiceImpl.class);

	private PriceRepository priceRepository;

	@Autowired
	public PriceServiceImpl(PriceRepository priceRepository) {
		this.priceRepository = priceRepository;
	}

	@Override
	public Price findById(long id) {
		return priceRepository.findById(id);
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
	public Price insert(Price price) {
		Price dbPrice = priceRepository.findById(price.getId());
		return null;
	}

	@Override
	public List<Price> findByList() {
		return null;
	}

	@Override
	public Page<Price> findByPage(Price price, Pageable pageable) {
		return null;
	}

	@Override
	public Price findById(String id) {
		return null;
	}

	@Override
	public Price update(Price price) {
		Price dbPrice = priceRepository.findById(price.getId());
		return null;
	}

	@Override
	public long count(Price price) {
		return 0;
	}
}