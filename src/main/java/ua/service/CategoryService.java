package ua.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.entity.Category;

import java.util.List;

/**
 * Created by Admin on 1/21/2017.
 */
public interface CategoryService {
    List<Category> findAll();

    void delete(int id);

    Category findOne(int id);

    Category findByName(String name);

    void save(Category category);

    void addTitle(int id, int titleId);

    void deleteTitle(int id, int titleId);

    Category loadedCategory(Integer id);

    Page<Category> findAll(Pageable pageable);
}
