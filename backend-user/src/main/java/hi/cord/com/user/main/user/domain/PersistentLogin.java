package hi.cord.com.user.main.user.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity(name="TB_USER_PERSISTENT_LOGIN")
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@Getter
@Setter
public class PersistentLogin implements Serializable{
	@Id
	@Column(name="PERSISTENT_LOGIN_SERIES", length=50)
	private String series;

	@Column(name="PERSISTENT_LOGIN_EMAIL", unique=true, nullable=false, length=60)
	private String email;
	
	@Column(name="PERSISTENT_LOGIN_TOKEN", unique=true, nullable=false)
	private String token;
	
	@Column(name="PERSISTENT_LOGIN_LATESTDATE", nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
	private Date createdDate;

}
