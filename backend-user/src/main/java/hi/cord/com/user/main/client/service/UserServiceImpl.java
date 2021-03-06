package hi.cord.com.user.main.client.service;

import hi.cord.com.common.domain.enumtypes.CommonState;
import hi.cord.com.user.main.client.domain.User;
import hi.cord.com.user.main.client.domain.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userDetailsService")
public class UserServiceImpl implements UserService, UserDetailsService {
    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public User insert(User user) {
        LOG.debug("p : save : {} ", user.toString());
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setState(CommonState.ACTIVE);
        return userRepository.save(user);
    }

    @Override
    public User findById(long id) {
        LOG.debug("p : findById : {} ", id);
        return null;
    }

    @Override
    public User findByIdx(long idx) {
        return null;
    }

    @Override
    public User findByEmail(String userEmail) {
        LOG.debug("p : findByEmail : {} ", userEmail);
        return userRepository.findByEmail(userEmail);
    }

    @Override
    public User findByNickname(String nickname) {
        return null;
    }


    @Override
    public List<User> findByList() {
        return null;
    }

    @Override
    public Page<User> findByPage(User user, Pageable pageable) {
        return null;
    }

    @Override
    public User findById(String id) {
        return null;
    }

    @Override
    public User updateByNickname(User user) {
        LOG.debug("p  : update : {} ", user.toString());
        User dbUser = userRepository.findByNickname(user.getNickname());
        if (dbUser != null) {
            dbUser.setName(user.getName());
        }
        return user;
    }

    @Override
    public User updateById(User user, String accessBy) {
        User dbUser = userRepository.findByEmail(user.getEmail());
        if (dbUser == null) {
            return null;
        }

        dbUser.setName(user.getName());
        return user;
    }

    @Override
    public User updateByIdx(User user, String accessBy) {
        return null;
    }

    @Override
    public User deleteByEmail(String userEmail) {
        LOG.debug("p : deleteUserByEmail : {} ", userEmail);
        return userRepository.deleteByEmail(userEmail);
    }

    @Override
    public User deleteByNickname(String nickname) {
        return null;
    }

    @Override
    public boolean deleteById(String id, String accessBy) {
        return false;
    }

    @Override
    public boolean deleteById(long id, String accessBy) {
        return false;
    }

    @Override
    public boolean deleteByIdx(long idx, String accessBy) {
        return false;
    }


    @Override
    public long count(User user) {
        return 0;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByNickname(username);
    }
}