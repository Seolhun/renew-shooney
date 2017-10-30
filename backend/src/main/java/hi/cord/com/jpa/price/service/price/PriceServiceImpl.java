package hi.cord.com.jpa.price.service.price;

import hi.cord.com.jpa.price.domain.Price;
import hi.cord.com.jpa.price.repository.PriceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("priceService")
@Transactional
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
	public Price insert(Price price) {
		Price dbPrice = priceRepository.findById(price.getId());
		return null;
	}

	@Override
	public Price update(Price price) {
		Price dbPrice = priceRepository.findById(price.getId());
		return null;
	}

	@Override
	public void deleteById(long id) {
		Price dbPrice = priceRepository.findById(id);
	}

	@Override
	public List<Price> findAllBy(Price price) {
		return priceRepository.findAllBy(price);
	}
}