package hi.cord.com.pay.main.domain.history;

import hi.cord.com.common.domain.entity.BaseEntity;
import hi.cord.com.pay.main.domain.price.Price;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity(name = "TB_PAID_HISTORY")
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@Getter
@Setter
public class PaidHistory extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private long id;

	@ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(foreignKey = @ForeignKey(name = "HISTORY_PRICE_FK"), name = "HISTORY_PRICE_ID", referencedColumnName = "PRICE_ID")
	private Price priceHistory;
}

