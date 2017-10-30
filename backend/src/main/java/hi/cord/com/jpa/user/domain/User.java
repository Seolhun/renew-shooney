package hi.cord.com.jpa.user.domain;

import hi.cord.com.common.domain.CommonDomainInfo;
import hi.cord.com.common.domain.CommonState;
import hi.cord.com.jpa.price.domain.history.PaidHistory;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The type User.
 */
@Entity(name = "TB_USER")
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@Getter
@Setter
public class User extends CommonDomainInfo implements Serializable {
	private static final long serialVersionUID = -3474096703802541016L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="USER_ID")
	private long id;

	// User, What did you paid money for service. or How many
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "paidWhoHistory", cascade=CascadeType.ALL)
	private List<PaidHistory> paidList;
	
	// User, How many have Privileges.
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "TB_PROFILE_REFER", foreignKey = @ForeignKey(name = "REFER_FK"), joinColumns = {
			@JoinColumn(name = "USER_ID", columnDefinition = "BIGINT(20)") }, inverseForeignKey = @ForeignKey(name = "PROFILE_REFER_FK"), inverseJoinColumns = {
					@JoinColumn(name = "PROFILE_ID") })
	private Set<UserProfile> profiles = new HashSet<>();

	/*
	    User UK Email
	*/
	@Pattern(regexp = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,3})$", message = "INVALID-EMAIL")
	@Column(name = "EMAIL", length = 60, unique = true, nullable = false)
	private String email;

    /*
        User UK Nickname
    */
    @NotNull
    @Column(name = "NICKNAME", length = 60, unique = true, nullable = false)
    private String nickname;

	@Column(name = "NAME", length = 60)
	private String name;

    //	User Photo to show others
	@Column(name = "PROFILE_IMAGE", length = 100)
	private String profileImage;

	// @Pattern(regexp="((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+=-~`]).{8,20})",
	// message="Invalid Password")
	// User Bcrypt encod Password
	@NotNull
	@Column(name = "PASSWORD", length = 100, nullable = false)
	private String password;

    @Column(name = "PHONE", length = 60)
    private String phone;
    /*
    About Address
    */
	@Column(name = "ZIP_CODE", length = 50)
	private String zipCode;

	@Column(name = "ADDRESS", length = 100)
	private String address;
	
	@Column(name = "NATION_CODE", length = 10)
	private String nation;

	/*
	About Point
	*/
	@Column(name = "ACTIVE_POINT", length = 10)
	private int activePoint;

	@Column(name = "PAID_POINT", length = 10)
	private int paidPoint;

	/*
	About subscribe
    */
	@Column(name = "IS_RECEIVE_MAIL")
	private boolean isReceiveEmail;

	// User boolean if receive message or not through the Phone
	@Column(name = "IS_RECEIVE_PHONE")
	private boolean isReceivePhone;

	/*
	User State detail
	*/
	// User, about Account state
	@Column(name = "STATE", length = 20)
	private CommonState state;

	// User, Boolean account is NON_EXPIRED or not.
	@Column(name = "IS_ACCOUNT_NON_EXPIRED", length = 1)
	private boolean isAccountNonExpired;

	// User, Boolean account is NON_EXPIRED or not.
	@Column(name = "IS_ACCOUNT_NON_LOCKED", length = 1)
	private boolean isAccountNonLocked;

	// User, Boolean account is CREDENTIALS_NON_EXPIRED or not.
	@Column(name = "IS_CREDENTIALS_NONEXPIRED", length = 1)
	private boolean isCredentialsNonExpired;

	// User, Boolean account is NON_LOCKED or not.
	@Column(name = "IS_ENABLED", length = 1)
	private boolean isEnabled;

	/*
	If user locked encode PK value to send email
	*/
    @Column(name = "LOCKED_AUTH", length = 100)
    private String userLockedAuth;

	/*
	User is checked about agreement
	*/
	// User, Boolean account is NON_LOCKED or not.
	@Column(name = "PRIVATE_AGREE", length = 1, nullable=false)
	private boolean userPrivateAgree;
	
	// User, Boolean account is NON_LOCKED or not.
	@Column(name = "SERVICE_AGREE", length = 1, nullable=false)
	private boolean userServiceAgree;

	@Transient
    private String ip;
}