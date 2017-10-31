package hi.cord.com.config.security;

import hi.cord.com.config.security.custom.CustomSuccessHandler;
import hi.cord.com.config.security.custom.LimitingDaoAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PersistentTokenRepository tokenRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/**").permitAll()
        .and()
            .formLogin().loginPage("/user/login")
            .loginProcessingUrl("/user/login")
            .usernameParameter("userEmail").passwordParameter("userPassword")
            .successHandler(customSuccessHandler())
            .failureUrl("/user/login?error")
        .and()
            .logout()
            .logoutUrl("/logout")
            .deleteCookies("remember-me")
            .deleteCookies("JSESSIONID")
            .invalidateHttpSession(true)
        .and()
            .rememberMe()
            .rememberMeParameter("remember-me").tokenRepository(tokenRepository).tokenValiditySeconds(86400)
        .and()
            .csrf()
        .and()
            .exceptionHandling().accessDeniedPage("/");

        //한글 인코딩 필터
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        http.addFilterBefore(filter, CsrfFilter.class);
    }

    @Bean
    public PersistentTokenBasedRememberMeServices getPersistentTokenBasedRememberMeServices() {
        return new PersistentTokenBasedRememberMeServices("remember-me", userDetailsService, tokenRepository);
    }

    @Bean
    public AuthenticationTrustResolver getAuthenticationTrustResolver() {
        return new AuthenticationTrustResolverImpl();
    }

    //비밀번호 인코딩 방법 BCrypt
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //로그인 시 인증정보 제공 프록시
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        LimitingDaoAuthenticationProvider provider = new LimitingDaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }

    //로그인 성공시
    @Bean
    public CustomSuccessHandler customSuccessHandler() {
        return new CustomSuccessHandler();
    }
}
