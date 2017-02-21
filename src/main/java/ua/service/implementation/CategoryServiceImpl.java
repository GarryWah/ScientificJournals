package ua.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.entity.Category;

import ua.entity.JournalTitle;
import ua.repository.CategoryRepository;
import ua.repository.JournalTitleRepository;
import ua.service.CategoryService;

import java.util.List;

import static ua.entity.Journal_.title;

/**
 * Created by Admin on 1/21/2017.
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private JournalTitleRepository journalTitleRepository;
    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public void delete(int id) {
        categoryRepository.delete(id);

    }

    @Override
    public Category findOne(int id) {
        return categoryRepository.findOne(id);

    }

    @Override
    public Category findByName(String name) {
        return categoryRepository.findByName(name);
    }

    @Override
    public void save(Category category) {
        categoryRepository.save(category);

    }
    @Override
    @Transactional
    public void addTitle(int id, int titleId) {
        Category category=categoryRepository.loadedCategory(id);
        JournalTitle journalTitle=journalTitleRepository.loadedTitle(titleId);
        category.getTitles().add(journalTitle);
        categoryRepository.save(category);
        journalTitleRepository.save(journalTitle);

    }
    @Override
    @Transactional
    public void deleteTitle(int id, int titleId) {
        Category category=categoryRepository.loadedCategory(id);
        JournalTitle journalTitle=journalTitleRepository.loadedTitle(titleId);
        category.getTitles().removeIf(s->s.getId()==titleId);
       journalTitleRepository.delete(titleId);
        categoryRepository.save(category);
        journalTitleRepository.save(journalTitle);
    }

    @Override
    public Page<Category> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Override
    public Category loadedCategory(Integer id) {
        return categoryRepository.loadedCategory(id);
    }
}
