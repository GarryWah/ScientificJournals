package ua.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.entity.Order;

import java.util.List;


/**
 * Created by Admin on 1/21/2017.
 */
public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Query("SELECT o FROM Order o LEFT JOIN FETCH o.user LEFT JOIN FETCH o.journal j LEFT JOIN FETCH " +
            "j.title WHERE o.id=:id")
    Order findOne(@Param("id") Integer id);
    @Query("SELECT o FROM Order o LEFT JOIN FETCH o.user LEFT JOIN FETCH o.journal j LEFT JOIN FETCH " +
            "j.title")
    List<Order> findAll(@Param("id") Integer id);
}
