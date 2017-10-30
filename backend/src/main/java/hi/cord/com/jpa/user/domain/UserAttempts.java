package hi.cord.com.jpa.user.domain;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "TB_ATTEMPTS")
@Data
public class UserAttempts implements Serializable {
	private static final long serialVersionUID = -6645634619910097302L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "ATTEMPTS_EMAIL", nullable = false)
	private String email;

	@Column(name = "ATTEMPTS_COUNTS", nullable = false)
	private int userAttemptsCounts;

	@Column(name = "ATTEMPTS_CREATED_DATE", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date createdDate;

	@Column(name = "ATTEMPTS_LOGIN_IP")
	private String ip;

	@Column(name = "IS_SUCCESS")
	private boolean isSuccess;

	public UserAttempts() {

	}

	public UserAttempts(String email, int userAttemptsCounts, String ip) {
		this.email = email;
		this.userAttemptsCounts = userAttemptsCounts;
		this.ip = ip;
	}

	public UserAttempts(String email, int userAttemptsCounts, String ip, boolean isSuccess) {
		this.email = email;
		this.userAttemptsCounts = userAttemptsCounts;
		this.ip = ip;
		this.isSuccess = isSuccess;
	}
}
