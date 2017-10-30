package hi.cord.com.config.security.custom;

import hi.cord.com.common.domain.CommonState;
import hi.cord.com.jpa.user.domain.User;
import hi.cord.com.jpa.user.domain.UserProfile;
import hi.cord.com.jpa.user.servie.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

	private static final Logger LOG = LoggerFactory.getLogger(CustomUserDetailsService.class);

	private UserService userService;

	@Autowired
	public CustomUserDetailsService(UserService userService) {
		this.userService = userService;
	}

	@Transactional(readOnly = true)
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		try {
			User user = userService.findByEmail(email);
			LOG.info("param - User : {}", user);
			if (user == null) {
				LOG.info("User not found");
				throw new UsernameNotFoundException("User not found");
			}
			return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), user.getState() == (CommonState.ACTIVE), true, true, true, getGrantedAuthorities(user));
		} catch (Exception e){
			throw new UsernameNotFoundException("Not user");
		}
	}

	private List<GrantedAuthority> getGrantedAuthorities(User user) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		for (UserProfile userProfile : user.getProfiles()) {
			LOG.info("param - UserProfile : {}", userProfile.toString());
			authorities.add(new SimpleGrantedAuthority("ROLE_" + userProfile.getUserProfileType()));
		}
		
		LOG.info("authorities : {}", authorities);
		return authorities;
	}

}
