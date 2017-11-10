package hi.cord.com.config.security.converter;

import hi.cord.com.user.user.domain.profile.UserProfile;
import hi.cord.com.user.user.servie.profile.UserProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * The type Role to user profile converter.
 */
@Component
public class RoleToUserProfileConverter implements Converter<Object, UserProfile> {
	private static final Logger LOG = LoggerFactory.getLogger(RoleToUserProfileConverter.class);

	private UserProfileService userProfileService;

	@Autowired
	public RoleToUserProfileConverter(UserProfileService userProfileService) {
		this.userProfileService = userProfileService;
	}

	/**
	 * Gets ProfilePrivilege by Id
	 * 
	 * @see Converter#convert(Object)
	 */
	public UserProfile convert(Object element) {
		Integer id = Integer.parseInt((String) element);
		UserProfile profile = userProfileService.findById(id);
		return profile;
	}

}