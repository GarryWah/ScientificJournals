package ua.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.entity.User;


/**
 * Created by Admin on 1/21/2017.
 */

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT distinct u FROM User u LEFT JOIN FETCH u.orders WHERE "
            + "u.username=:username")
    User findByUsername(@Param("username") String username);

    @Query("SELECT distinct u FROM User u LEFT JOIN FETCH u.orders WHERE "
            + "u.id=:id")
    User loadedUser(@Param("id") Integer id);

    User findByEmail(String email);

    Page<User> findAll(Pageable pageable);
}
