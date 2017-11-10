package hi.cord.com.pay.main.service.price;

import hi.cord.com.pay.main.domain.price.Price;
import hi.cord.com.pay.main.domain.price.PriceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
    public Price findById(long id) {
        return priceRepository.findById(id);
    }

    @Override
    public Price findByIdx(long idx) {
        return null;
    }

    @Override
    public boolean deleteById(String id, String accessBy) {
        return false;
    }

    @Override
    public boolean deleteById(long id, String accessBy) {
        return false;
    }

    @Override
    public boolean deleteByIdx(long idx, String accessBy) {
        return false;
    }

    @Override
    public Price updateById(Price price, String accessBy) {
        Price dbPrice = priceRepository.findById(price.getId());
        return null;
    }

    @Override
    public long count(Price price) {
        return 0;
    }
}