package ua.service.implementation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ua.entity.Role;
import ua.entity.User;
import ua.repository.UserRepository;
import ua.service.UserService;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by Admin on 1/21/2017.
 */
@Service("userDetailsService")
public class UserServiceImpl implements UserDetailsService, UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void delete(int id) {
        userRepository.delete(id);
    }

    @Override
    public User findOne(Integer id) {
        return userRepository.loadedUser(id);
    }

    @Override
    public User findByUsername(String username) {
        return (User) loadUserByUsername(username);
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public BCryptPasswordEncoder getEncoder() {
        return encoder;
    }

    public void setEncoder(BCryptPasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @Override
    public void save(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole(Role.ROLE_USER);
        userRepository.save(user);
    }

    @PostConstruct
    public void admin() {
        User user = userRepository.findByUsername("admin");
        if (user == null) {
            user = new User();
            user.setEmail("");
            user.setPassword(encoder.encode("admin"));
            user.setRole(Role.ROLE_ADMIN);
            user.setUsername("admin");
            userRepository.save(user);
        }
    }


    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }
}
