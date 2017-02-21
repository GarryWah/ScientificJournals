package ua.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.entity.Category;

/**
 * Created by Admin on 1/21/2017.
 */
public interface CategoryRepository extends JpaRepository<Category, Integer> {
  @Query("SELECT distinct c FROM Category c LEFT JOIN FETCH c.titles WHERE "
            + "c.id=:id")
    Category loadedCategory(@Param("id") Integer id);
    Category findByName(String name);
    Page<Category> findAll(Pageable pageable);
}
