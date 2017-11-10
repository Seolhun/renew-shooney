package hi.cord.com.user.main.user.domain.attempts;

import hi.cord.com.user.common.domain.CreatedByEntity;
import hi.cord.com.user.common.domain.ModifiedByEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity(name = "TB_ATTEMPTS")
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@Getter
@Setter
public class UserAttempts implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ATTEMPTS_ID")
	private Long id;

	@Column(name = "ATTEMPTS_EMAIL", nullable = false)
	private String email;

	@Column(name = "ATTEMPTS_COUNTS", nullable = false)
	private int attemptsCounts;

	@Column(name = "ATTEMPTS_CREATED_DATE", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date createdDate;

	@Column(name = "ATTEMPTS_LOGIN_IP")
	private String ip;

	@Column(name = "IS_SUCCESS")
	private boolean isSuccess;

	@CreatedBy
	@AssociationOverrides({
			@AssociationOverride(name = "user", joinColumns = @JoinColumn(name = "CREATED_BY"))
	})
	@AttributeOverrides({
			@AttributeOverride(name = "user", column = @Column(name = "CREATED_BY")),
			@AttributeOverride(name = "nickname", column = @Column(name = "CREATED_NICKNAME", length = 60))
	})
	@Embedded
	private CreatedByEntity createdByEntity;

	@LastModifiedBy
	@AssociationOverrides({
			@AssociationOverride(name = "user", joinColumns = @JoinColumn(name = "LAST_MODIFIED_BY"))
	})
	@AttributeOverrides({
			@AttributeOverride(name = "user", column = @Column(name = "LAST_MODIFIED_BY")),
			@AttributeOverride(name = "nickname", column = @Column(name = "LAST_MODIFIED_NICKNAME", length = 60))
	})
	@Embedded
	private ModifiedByEntity modifiedByEntity;

	public UserAttempts() {

	}

	public UserAttempts(String email, int attemptsCounts, String ip) {
		this.email = email;
		this.attemptsCounts = attemptsCounts;
		this.ip = ip;
	}

	public UserAttempts(String email, int attemptsCounts, String ip, boolean isSuccess) {
		this.email = email;
		this.attemptsCounts = attemptsCounts;
		this.ip = ip;
		this.isSuccess = isSuccess;
	}
}
