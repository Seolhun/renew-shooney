package hi.cord.com.jpa.user;

import hi.cord.com.common.service.CommonService;
import hi.cord.com.jpa.price.service.record.PriceRecordService;
import hi.cord.com.jpa.user.domain.User;
import hi.cord.com.jpa.user.domain.UserProfile;
import hi.cord.com.jpa.user.domain.UserProfileType;
import hi.cord.com.jpa.user.servie.profile.UserProfileService;
import hi.cord.com.jpa.user.servie.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("")
public class UserRestController {
	private static final Logger LOG = LoggerFactory.getLogger(UserRestController.class);

	private UserService userService;
	private UserProfileService userProfileService;
	private PriceRecordService priceRecordService;
	private MessageSource messageSource;
	private PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;
	private AuthenticationTrustResolver authenticationTrustResolver;
	private CommonService commonService;

	@Autowired
	public UserRestController(UserService userService, PriceRecordService priceRecordService, MessageSource messageSource, UserProfileService userProfileService, PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices, AuthenticationTrustResolver authenticationTrustResolver, CommonService commonService) {
		this.userService = userService;
		this.priceRecordService = priceRecordService;
		this.messageSource = messageSource;
		this.userProfileService = userProfileService;
		this.persistentTokenBasedRememberMeServices = persistentTokenBasedRememberMeServices;
		this.authenticationTrustResolver = authenticationTrustResolver;
		this.commonService = commonService;
	}

	/**
	 * User email duplication Check
	 *
	 * @param email the email
	 * @return ResponseEntity response entity
	 */
	@GetMapping(value = { "/duplication/email/{email}" })
	public ResponseEntity emailDupl(@PathVariable String email) {
		if (email == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A email is requirement");
		} else if (!commonService.validPattern(email, "email")) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This "+email+" is not Email form");
		} else if (userService.findByEmail(email) != null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(email + " already was used.");
		}
		return ResponseEntity.status(HttpStatus.OK).body(email + " is available");
	}

	/**
	 * User email duplication Check
	 *
	 * @param nickname the nickname
	 * @return the response entity
	 */
	@GetMapping(value = { "/duplication/nickname/{nickname}" })
	public ResponseEntity nickname(@PathVariable String nickname) {
		if (nickname == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A nickname is requirement");
		} else if (!commonService.validPattern(nickname, "nickname")) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This "+nickname+" is not Nickname form");
		} else if (userService.findByNickname(nickname) != null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(nickname + " already was used.");
		}
		return ResponseEntity.status(HttpStatus.OK).body(nickname + " is available");
	}



	/**
	 * Login page response entity.
	 *
	 * @param error   the error
	 * @param request the request
	 * @return the response entity
	 */
	@GetMapping(value = "/login")
	public ResponseEntity loginPage(@RequestParam(value = "error", required = false) String error, HttpServletRequest request) {
		LOG.info("loginPage");
		if (isCurrentAuthenticationAnonymous()) {
			if (error != null) {
				Exception exception = (Exception)request.getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(getErrorMessage(exception));
			}
			return ResponseEntity.status(HttpStatus.TEMPORARY_REDIRECT).body("Must be logined");
		}
		return ResponseEntity.status(HttpStatus.OK).body("Try login");
	}

	/**
	 * Signup do response entity.
	 *
	 * @param user    the user
	 * @param result  the result
	 * @param request the request
	 * @return the response entity
	 *
	 * @throws Exception the exception
	 */
	@PostMapping(value = { "/signup" })
	public ResponseEntity signupDo(@Valid User user, BindingResult result, HttpServletRequest request) throws Exception {
		String mapping = "views/user/user-signup";
		String email=user.getEmail();
		String nickname=user.getNickname();
		String userAddress=user.getAddress();

		// 개인 별로 에러메세지 띄우기 구현 예정(개인별로 해도 메세지가 2개뜨는 문제 발생)
		if (email == null || email.length() == 0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email is requirements.");
		} else if (nickname == null || nickname.length() == 0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nickname is requirements.");
		} else if (result.hasErrors()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Write requirements information.");
		}

		if (userService.findByEmail(user.getEmail() ) != null ) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email already registered");
		} else if (userService.findByNickname(user.getNickname() ) != null ) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email already registered");
		}

		// 유저 권한 넣기(프론트에서 값을 받지 않기때문에 백엔드에서 넣어준다.)
		Set<UserProfile> upSet = new HashSet<>();
		UserProfile userProfile = new UserProfile();
		userProfile.setProfileId(UserProfileType.PLAYER.ordinal() + 1);
		userProfile.setProfileType(UserProfileType.PLAYER.getType());
		upSet.add(userProfile);
		user.setProfiles(upSet);
		userService.insert(user);

		return ResponseEntity.status(HttpStatus.OK).body(user);
	}

	/**
	 * ##########################################
	 * ############### Method Div ###############
	 * ##########################################
	 */
	private boolean isCurrentAuthenticationAnonymous() {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authenticationTrustResolver.isAnonymous(authentication);
	}

	// 커스텀 된 로그인 에러 메세지
	private String getErrorMessage(Exception exception) {
		String error = "";
		if (exception instanceof LockedException) {
			error = "현재 계정이 잠겼습니다.";
		} else if (exception instanceof DisabledException) {
			error = "현재 계정이 이용 불가능합니다.";
//		} else if (exception instanceof RecapException) {
//			error = "현재 계정이 이용 불가능합니다.";
		} else {
			error = "계정과 비밀번호를 올바르게 입력해주세요,.";
		}
		return error;
	}
}