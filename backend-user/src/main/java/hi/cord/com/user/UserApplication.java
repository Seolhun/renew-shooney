package hi.cord.com.user;

import hi.cord.com.common.CommonApplication;
import hi.cord.com.user.config.YAMLConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

@SpringBootApplication
@Import(CommonApplication.class)
public class UserApplication extends AuthorizationServerConfigurerAdapter implements CommandLineRunner {
    private static final Logger LOG = LoggerFactory.getLogger(UserApplication.class);
    private YAMLConfig myConfig;

    @Autowired
    public UserApplication(YAMLConfig myConfig) {
        this.myConfig = myConfig;
    }

    @Override
    public void run(String... args) throws Exception {
        LOG.debug("p : messages : {}", myConfig.getMessage());
        LOG.debug("p : name : {}", myConfig.getName());
        LOG.debug("p : servers : {}", myConfig.getServers());
    }

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(UserApplication.class);
        app.run();
    }

    // ...
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
    // OAuth2 인증서버 자체의  보안 정보를 설정하는 부분
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    // Client 에 대한 정보를  설정하는 부분
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    // OAuth2 서버가 작동하기 위한 Endpoint에 대한 정보를 설정
    }
    // ...
}
