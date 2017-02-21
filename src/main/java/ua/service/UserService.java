package ua.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.entity.User;

import java.util.List;

/**
 * Created by Admin on 1/21/2017.
 */
public interface UserService {
    List<User> findAll();
    void delete(int id);
    User findOne(Integer id);
    User findByUsername(String username);
    void save(User user);
    User findByEmail(String email);
    Page<User> findAll(Pageable pageable);
}
