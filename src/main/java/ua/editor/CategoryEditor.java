package ua.editor;

import ua.entity.Category;
import ua.service.CategoryService;

import java.beans.PropertyEditorSupport;

/**
 * Created by Admin on 1/23/2017.
 */
public class CategoryEditor extends PropertyEditorSupport {
    private final CategoryService categoryService;

    public CategoryEditor(CategoryService categoryService) {

        this.categoryService = categoryService;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        Category category = categoryService.findOne(Integer.valueOf(text));
        setValue(category);
    }
}
