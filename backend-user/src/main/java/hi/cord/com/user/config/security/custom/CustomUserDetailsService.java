package hi.cord.com.user.config.security.custom;

import hi.cord.com.user.common.domain.enumtypes.CommonState;
import hi.cord.com.user.main.user.domain.profile.UserProfile;
import hi.cord.com.user.main.user.domain.user.User;
import hi.cord.com.user.main.user.servie.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
    private static final Logger LOG = LoggerFactory.getLogger(CustomUserDetailsService.class);

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        try {
            User user = userService.findByEmail(email);
            LOG.debug("p : User : {}", user);
            if (user == null) {
                throw new UsernameNotFoundException("User not found");
            }
            return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), user.getState() == (CommonState.ACTIVE), true, true, true, getGrantedAuthorities(user));
        } catch (Exception e) {
            throw new UsernameNotFoundException("Not user");
        }
    }

    private List<GrantedAuthority> getGrantedAuthorities(User user) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        for (UserProfile userProfile : user.getProfiles()) {
            LOG.debug("p : ProfilePrivilege : {}", userProfile.toString());
            authorities.add(new SimpleGrantedAuthority("ROLE_" + userProfile.getType()));
        }
        return authorities;
    }

}
