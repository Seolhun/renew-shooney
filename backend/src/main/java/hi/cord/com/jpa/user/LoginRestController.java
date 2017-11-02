package hi.cord.com.jpa.user;

import hi.cord.com.common.service.CommonService;
import hi.cord.com.jpa.price.service.history.PaidHistoryService;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("")
public class LoginRestController {
    private static final Logger LOG = LoggerFactory.getLogger(LoginRestController.class);

    private UserService userService;
    private UserProfileService userProfileService;
    private PaidHistoryService paidHistoryService;
    private MessageSource messageSource;
    private PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;
    private AuthenticationTrustResolver authenticationTrustResolver;
    private CommonService commonService;

    @Autowired
    public LoginRestController(UserService userService, PaidHistoryService paidHistoryService, MessageSource messageSource, UserProfileService userProfileService, PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices, AuthenticationTrustResolver authenticationTrustResolver, CommonService commonService) {
        this.userService = userService;
        this.paidHistoryService = paidHistoryService;
        this.messageSource = messageSource;
        this.userProfileService = userProfileService;
        this.persistentTokenBasedRememberMeServices = persistentTokenBasedRememberMeServices;
        this.authenticationTrustResolver = authenticationTrustResolver;
        this.commonService = commonService;
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
        if (isCurrentAuthenticationAnonymous()) {
            if (error != null) {
                Exception exception = (Exception) request.getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(getErrorMessage(exception));
            }
            return ResponseEntity.status(HttpStatus.TEMPORARY_REDIRECT).body("Must be logined");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Try login");
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