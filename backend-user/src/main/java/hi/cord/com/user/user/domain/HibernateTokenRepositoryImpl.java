package hi.cord.com.user.user.domain;

import hi.cord.com.common.repository.AbstractRepository;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository("tokenRepositoryDao")
public class HibernateTokenRepositoryImpl extends AbstractRepository<String, PersistentLogin> implements PersistentTokenRepository {
	private static final Logger LOG = LoggerFactory.getLogger(HibernateTokenRepositoryImpl.class);

	@Override
	public void createNewToken(PersistentRememberMeToken token) {
		LOG.debug("Creating Token for user : {}", token.getUsername());
		PersistentLogin persistentLogin = new PersistentLogin();
		persistentLogin.setEmail(token.getUsername());
		persistentLogin.setSeries(token.getSeries());
		persistentLogin.setToken(token.getTokenValue());
		persist(persistentLogin);
	}

	// persistent Login 부
	@Override
	public PersistentRememberMeToken getTokenForSeries(String seriesId) {
		LOG.debug("Fetch Token if any for seriesId : {}", seriesId);
		try {
			Criteria crit = createEntityCriteria();
			crit.add(Restrictions.eq("series", seriesId));
			PersistentLogin persistentLogin = (PersistentLogin) crit.uniqueResult();

			return new PersistentRememberMeToken(persistentLogin.getEmail(), persistentLogin.getSeries(), persistentLogin.getToken(), persistentLogin.getCreatedDate());
		} catch (Exception e) {
			LOG.debug("Token not found...");
			return null;
		}
	}

	@Override
	public void removeUserTokens(String email) {
		LOG.debug("Removing Token if any for user : {}", email);
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("email", email));
		PersistentLogin persistentLogin = (PersistentLogin) crit.uniqueResult();
		if (persistentLogin != null) {
			LOG.debug("rememberMe was selected");
			delete(persistentLogin);
		}
	}

	@Override
	public void updateToken(String seriesId, String tokenValue, Date lastUsed) {
		LOG.debug("Updating Token for seriesId : {}", seriesId);
		PersistentLogin persistentLogin = getByKey(seriesId);
		persistentLogin.setToken(tokenValue);
		update(persistentLogin);
	}
}
