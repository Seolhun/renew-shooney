package hi.cord.com.jpa.price.domain;

import hi.cord.com.common.domain.CommonDomainInfo;
import hi.cord.com.jpa.user.domain.User;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "TB_RECORD")
@Data
public class PriceRecord extends CommonDomainInfo {
	@ManyToOne(fetch = FetchType.LAZY, optional=true, cascade=CascadeType.ALL)
	@JoinColumn(foreignKey = @ForeignKey(name = "RECORD_USER_FK"), name = "RECORD_PAID_BY_USER_ID", referencedColumnName = "USER_ID", nullable=true)
	private User userPiadRecords;

	@ManyToOne(fetch = FetchType.LAZY, optional=true, cascade=CascadeType.ALL)
	@JoinColumn(foreignKey = @ForeignKey(name = "RECORD_FK"), name = "RECORD_VALUE_FROM_ID", referencedColumnName = "ID", nullable=true)
	private Price priceRecordValueFromPrice;
}
