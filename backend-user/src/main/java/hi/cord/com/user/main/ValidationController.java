package hi.cord.com.user.main;

import hi.cord.com.common.service.common.CommonService;
import hi.cord.com.user.main.profile.service.UserProfileService;
import hi.cord.com.user.main.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author HunSeol
 * @Date 2017. 11. 1.
 * @IDE IntelliJ IDEA
 */
@RestController
@RequestMapping("/validation")
public class ValidationController {
    private static final Logger LOG = LoggerFactory.getLogger(ValidationController.class);

    private UserService userService;
    private UserProfileService userProfileService;
    private CommonService commonService;

    @Autowired
    public ValidationController(UserService userService, UserProfileService userProfileService, CommonService commonService) {
        this.userService = userService;
        this.userProfileService = userProfileService;
        this.commonService = commonService;
    }

    /**
     * Email duplication response entity.
     *
     * @param email the email
     * @return ResponseEntity response entity
     */
    @GetMapping(value = {"/email/{email}"})
    public ResponseEntity emailDuplication(@PathVariable String email) {
        if (email == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A email is requirement");
        } else if (!commonService.validPattern(email, "email")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This " + email + " is not Email form");
        } else if (userService.findByEmail(email) != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(email + " already was used.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(email + " is available");
    }

    /**
     * Nickname duplication response entity.
     *
     * @param nickname the nickname
     * @return the response entity
     */
    @GetMapping(value = {"/nickname/{nickname}"})
    public ResponseEntity nicknameDuplication(@PathVariable String nickname) {
        if (nickname == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A nickname is requirement");
        } else if (!commonService.validPattern(nickname, "nickname")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This " + nickname + " is not Nickname form");
        } else if (userService.findByNickname(nickname) != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(nickname + " already was used.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(nickname + " is available");
    }

//    @GetMapping(value="/csrf-token")
//    public ResponseEntity getCsrfToken(HttpServletRequest request) {
//        CsrfToken token = (CsrfToken)request.getAttribute(CsrfToken.class.getName());
//        String tokenValue = token.getToken();
//        String tokenHeader = token.getHeaderName();
//        return ResponseEntity.status(HttpStatus.OK).body("Token Header : "+tokenHeader+"\nToken Value : "+tokenValue);
//    }
}
