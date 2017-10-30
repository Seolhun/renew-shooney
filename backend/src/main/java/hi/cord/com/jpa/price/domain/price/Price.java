package hi.cord.com.jpa.price.domain.price;

import hi.cord.com.common.domain.CommonDomainInfo;
import hi.cord.com.jpa.price.domain.history.PaidHistory;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity(name = "TB_PRICE")
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@Getter
@Setter
public class Price extends CommonDomainInfo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="PRICE_ID")
    private long id;

	@Column(name = "SERVICE_NAME", nullable = false, length = 50)
	private String serviceName;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "priceHistory", cascade = CascadeType.ALL)
	private List<PaidHistory> paidHistories;

	@Column(name = "ORIGINAL_VALUE", nullable = false, length = 40)
	private int originalPrice;

	@Column(name = "DISCOUNTED_RATE")
	private int discountedRate;

	@Column(name = "DISCOUNTED_PRICE")
	private int discountedPrice;

	@Column(name = "STATE")
	private PriceState priceState;

	@Column(name = "STARTED_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date startedDate;

	@Column(name = "FINISHED_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date finishedDate;
}
