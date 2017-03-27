package ua.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ua.entity.Category;
import ua.service.CategoryService;

import java.util.regex.Pattern;

/**
 * Created by Admin on 1/30/2017.
 */
public class CategoryValidator implements Validator {
    private final CategoryService categoryService;
    private final static Pattern PATTERN = Pattern.compile("^[^\\d]+$");

    public CategoryValidator(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Category.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Category category = (Category) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "", "Can't be empty!");
        if (categoryService.findByName(category.getName()) != null) {
            errors.rejectValue("name", "", "Already exists!");
        }

        if (!category.getName().equals("") && !PATTERN.matcher(category.getName()).matches()) {
            errors.rejectValue("name", "", "Category not include digits!");
        }
    }
}
