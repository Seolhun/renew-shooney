package hi.cord.com.jpa.user.domain;

import hi.cord.com.common.domain.CommonDomainInfo;
import hi.cord.com.common.domain.CommonState;
import hi.cord.com.jpa.price.domain.PriceRecord;
import lombok.Data;

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
@Entity
@Data
@Table(name = "TB_USER")
public class User extends CommonDomainInfo implements Serializable {
	private static final long serialVersionUID = -3474096703802541016L;
	// User, What did you paid money for service. or How many
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userPiadRecords", cascade=CascadeType.ALL)
	private List<PriceRecord> paidList;
	
	// User, How many have Privileges.
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "TB_PROFILE_REFER", foreignKey = @ForeignKey(name = "REFER_FK"), joinColumns = {
			@JoinColumn(name = "ID", columnDefinition = "BIGINT(20)") }, inverseForeignKey = @ForeignKey(name = "PROFILE_REFER_FK"), inverseJoinColumns = {
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
    @Column(name = "NICK_NAME", length = 60, unique = true, nullable = false)
    private String nickname;

	@Column(name = "NAME", length = 60)
	private String name;

    //	User Photo to show others
	@Column(name = "PROFILE_IMAGE", length = 100)
	private String profileImage;

	// @Pattern(regexp="((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+=-~`]).{8,20})",
	// message="Invalid Password")
	// User Bcrypt encod Password
	@Column(name = "PASSWORD", length = 100, nullable = false)
	private String password;

    @Column(name = "PHONE", length = 60, unique = true)
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
	@Column(name = "STATE", length = 20, nullable = false)
	private CommonState state;

	// User, Boolean account is NON_EXPIRED or not.
	@Column(name = "ACCOUNT_NON_EXPIRED", length = 1, nullable=false)
	private boolean userAccountNonExpired;

	// User, Boolean account is CREDENTIALS_NON_EXPIRED or not.
	@Column(name = "CREDENTIALS_NON_EXPIRED", length = 1, nullable=false)
	private boolean userCredentialsNonExpired;

	// User, Boolean account is NON_LOCKED or not.
	@Column(name = "ACCOUNT_NON_LOCKED", length = 1, nullable=false)
	private boolean userAccountNonLocked;

	/*
	If user locked encode PK value to send email
	*/
    @Column(name = "LOCKED_AUTH", length = 100, nullable = true)
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