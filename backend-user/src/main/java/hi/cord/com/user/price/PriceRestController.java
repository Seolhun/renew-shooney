package hi.cord.com.user.price;

import hi.cord.com.common.service.common.CommonService;
import hi.cord.com.user.price.service.history.PaidHistoryService;
import hi.cord.com.user.price.service.price.PriceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/{nickname}/price")
public class PriceRestController {
    private static final Logger LOG = LoggerFactory.getLogger(PriceRestController.class);

	private PriceService priceService;
	private PaidHistoryService paidHistoryService;
	private CommonService commonService;

	@Autowired
	public PriceRestController(PriceService priceService, PaidHistoryService paidHistoryService, CommonService commonService) {
		this.priceService = priceService;
		this.paidHistoryService = paidHistoryService;
		this.commonService = commonService;
	}

	@GetMapping(value = "")
	public ResponseEntity findAll(@PathVariable String nickname) {

		return ResponseEntity.status(HttpStatus.OK).body("OK");
	}

    @PutMapping("")
    public ResponseEntity insert(@PathVariable String nickname) {

        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

    @GetMapping(value = "/{priceId}")
	public ResponseEntity findOne(@PathVariable String nickname, @PathVariable long priceId) {

        return ResponseEntity.status(HttpStatus.OK).body("OK");
	}

    @PutMapping(value = "/{priceId}")
	public ResponseEntity superAdminPriceDelete(@PathVariable String nickname, @PathVariable long priceId) {

        return ResponseEntity.status(HttpStatus.OK).body("OK");
	}
}
