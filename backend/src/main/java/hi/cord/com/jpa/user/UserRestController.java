package hi.cord.com.jpa.user;

import hi.cord.com.common.domain.pagination.Pagination;
import hi.cord.com.common.service.CommonService;
import hi.cord.com.jpa.price.service.history.PaidHistoryService;
import hi.cord.com.jpa.user.domain.profile.UserProfile;
import hi.cord.com.jpa.user.domain.profile.UserProfileType;
import hi.cord.com.jpa.user.domain.user.User;
import hi.cord.com.jpa.user.servie.profile.UserProfileService;
import hi.cord.com.jpa.user.servie.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/user")
public class UserRestController {
    private static final Logger LOG = LoggerFactory.getLogger(UserRestController.class);

    private UserService userService;
    private UserProfileService userProfileService;
    private PaidHistoryService paidHistoryService;
    private CommonService commonService;

    @Autowired
    public UserRestController(UserService userService, PaidHistoryService paidHistoryService, UserProfileService userProfileService, CommonService commonService) {
        this.userService = userService;
        this.paidHistoryService = paidHistoryService;
        this.userProfileService = userProfileService;
        this.commonService = commonService;
    }

    @GetMapping("")
    public ResponseEntity findAll(Pagination<User> pagination, Principal principal) {

        return ResponseEntity.status(HttpStatus.OK).body("Success");
    }

    /**
     * Save response entity.
     *
     * @param user          the user
     * @param bindingResult the binding result
     * @return the response entity
     */
    @PostMapping("")
    public ResponseEntity save(@RequestBody User user, BindingResult bindingResult) {
        String mapping = "views/user/user-signup";
        String email = user.getEmail();
        String nickname = user.getNickname();
        String userAddress = user.getCommonAddress().getAddress();

        // 개인 별로 에러메세지 띄우기 구현 예정(개인별로 해도 메세지가 2개뜨는 문제 발생)
        if (email == null || email.length() == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email is requirements.");
        } else if (nickname == null || nickname.length() == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nickname is requirements.");
        } else if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Write requirements information.");
        }

        if (userService.findByEmail(user.getEmail()) != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email already registered");
        } else if (userService.findByNickname(user.getNickname()) != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email already registered");
        }

        // 유저 권한 넣기(프론트에서 값을 받지 않기때문에 백엔드에서 넣어준다.)
        Set<UserProfile> userProfileSet = new HashSet<>();
        UserProfile userProfile = new UserProfile();
        //userProfile.setId(UserProfileType.GUEST.ordinal() +1);
        userProfile.setId(UserProfileType.GUEST.ordinal());
        userProfile.setType(UserProfileType.GUEST);

        userProfileSet.add(userProfile);
        user.setProfiles(userProfileSet);
        userService.insert(user);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }


    @GetMapping("/{nickname}")
    public ResponseEntity findOne(@PathVariable String nickname) {

        return ResponseEntity.status(HttpStatus.OK).body("Success");
    }

    @PutMapping("/{nickname}")
    public ResponseEntity updated(@PathVariable String nickname) {

        return ResponseEntity.status(HttpStatus.OK).body("Success");
    }

    @DeleteMapping("/{nickname}")
    public ResponseEntity delete(@PathVariable String nickname) {

        return ResponseEntity.status(HttpStatus.OK).body("Success");
    }
}