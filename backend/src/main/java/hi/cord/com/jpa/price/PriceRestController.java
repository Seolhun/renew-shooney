package hi.cord.com.jpa.price;

import hi.cord.com.common.service.CommonService;
import hi.cord.com.jpa.price.service.record.PriceRecordService;
import hi.cord.com.jpa.price.service.price.PriceService;
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
	private PriceRecordService priceRecordService;
	private CommonService commonService;

	@Autowired
	public PriceRestController(PriceService priceService, PriceRecordService priceRecordService, CommonService commonService) {
		this.priceService = priceService;
		this.priceRecordService = priceRecordService;
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
