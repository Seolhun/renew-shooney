package hi.cord.com.jpa.price.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import hi.cord.com.common.domain.CommonDomainInfo;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Data
@Table(name = "TB_PRICE")
public class Price extends CommonDomainInfo implements Serializable{
	@Column(name = "SERVICE_NAME", nullable = false, length = 50)
	private String serviceName;

	@OneToMany(fetch = FetchType.LAZY, mappedBy="priceRecordValueFromPrice" ,cascade=CascadeType.ALL)
	private List<PriceRecord> priceRecordWithPrice;

	@Column(name = "ORIGINAL_VALUE", nullable = false, length = 40)
	private int originalPrice;

	@Column(name = "DISCOUNT_RATE", length = 20)
	private String discountedRate;

	@Column(name = "DISCOUNTED_VALUE", length = 40)
	private int discountedValue;

	@Column(name = "STATE", nullable = false, length = 10)
	@Enumerated(EnumType.STRING)
	private PriceRecordState recordAllowState;

	@Column(name = "STARTED_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date startedDate;

	@Column(name = "FINISH_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date finishedDate;

	@Column(name = "DISCOUNT_RATE", length = 20)
	private int priceDiscountRate;

	@Column(name = "EVENT_START_DATE", nullable = false, updatable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date priceEventStartDate;
	
	@Column(name = "EVENT_END_DATE", nullable = false, updatable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date priceEventEndDate;
}
