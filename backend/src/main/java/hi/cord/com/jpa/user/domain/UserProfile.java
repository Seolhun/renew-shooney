package hi.cord.com.jpa.user.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.*;

@Entity(name="TB_USER_PROFILE")
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@Getter
@Setter
public class UserProfile implements Serializable{
	private static final long serialVersionUID = 3788477180129427170L;

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "PROFILE_ID")
	private int profileId;

	@Column(name="PROFILE_TYPE", length=15, unique=true, nullable=false)
	private String profileType = UserProfileType.PLAYER.getUserProfileType();
	
}
