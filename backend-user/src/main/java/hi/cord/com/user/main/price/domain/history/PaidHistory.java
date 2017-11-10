package hi.cord.com.user.main.price.domain.history;

import hi.cord.com.user.main.price.domain.price.Price;
import hi.cord.com.user.main.user.domain.user.User;
import lombok.Data;

import javax.persistence.*;

@Entity(name = "TB_PAID_HISTORY")
@Data
public class PaidHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private long id;

	@ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(foreignKey = @ForeignKey(name = "HISTORY_PAID_USER_FK"), name = "HISTORY_PAID_USER_ID", referencedColumnName = "USER_ID")
	private User paidWhoHistory;

	@ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(foreignKey = @ForeignKey(name = "HISTORY_PRICE_FK"), name = "HISTORY_PRICE_ID", referencedColumnName = "PRICE_ID")
	private Price priceHistory;
}

