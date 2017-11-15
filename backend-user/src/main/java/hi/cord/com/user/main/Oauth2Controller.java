package hi.cord.com.user.main;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class Oauth2Controller {

    @GetMapping("/oauth/user")
    public String user(Principal principal) {
        return principal.getName();
    }
}