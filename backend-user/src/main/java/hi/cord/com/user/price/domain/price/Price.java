package hi.cord.com.user.price.domain.price;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import hi.cord.com.common.domain.entity.BaseEntity;
import hi.cord.com.common.domain.entity.CreatedByEntity;
import hi.cord.com.common.domain.entity.ModifiedByEntity;
import hi.cord.com.common.domain.pagination.Pagination;
import hi.cord.com.user.price.domain.history.PaidHistory;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity(name = "TB_PRICE")
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@Getter
@Setter
public class Price extends BaseEntity implements Serializable {
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
	@Enumerated(EnumType.STRING)
	private PriceState priceState;

	@Column(name = "STARTED_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date startedDate;

	@Column(name = "FINISHED_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date finishedDate;

	@CreatedBy
	@AssociationOverrides({
			@AssociationOverride(name = "user", joinColumns = @JoinColumn(name = "CREATED_BY"))
	})
	@AttributeOverrides({
			@AttributeOverride(name = "user", column = @Column(name = "CREATED_BY", length = 60)),
			@AttributeOverride(name = "nickname", column = @Column(name = "CREATED_NICKNAME"))
	})
	@Embedded
	private CreatedByEntity createdByEntity;

	@LastModifiedBy
	@AssociationOverrides({
			@AssociationOverride(name = "user", joinColumns = @JoinColumn(name = "LAST_MODIFIED_BY"))
	})
	@AttributeOverrides({
			@AttributeOverride(name = "user", column = @Column(name = "LAST_MODIFIED_BY", length = 60)),
			@AttributeOverride(name = "nickname", column = @Column(name = "LAST_MODIFIED_NICKNAME"))
	})
	@Embedded
	private ModifiedByEntity modifiedByEntity;


	/**
	 * Requirement parameter in Entity
	 */
	@Transient
	@JsonSerialize
	@JsonDeserialize
	private Pagination<Price> pagination;

	//------------Entity Filed finished----------------
}
