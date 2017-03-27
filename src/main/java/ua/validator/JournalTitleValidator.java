package ua.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ua.entity.JournalTitle;
import ua.service.JournalTitleService;

import java.util.regex.Pattern;

/**
 * Created by Admin on 1/30/2017.
 */
public class JournalTitleValidator implements Validator {
    private final JournalTitleService journalTitleService;
    private final static Pattern PATTERN = Pattern.compile("^[^\\d]+$");

    public JournalTitleValidator(JournalTitleService journalTitleService) {
        this.journalTitleService = journalTitleService;
    }


    @Override
    public boolean supports(Class<?> aClass) {
        return JournalTitle.class.equals(aClass);
    }

    @Override

    public void validate(Object o, Errors errors) {
        JournalTitle journalTitle = (JournalTitle) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "", "Can't be empty!");
        if (journalTitle.getId() == null) {
            if (journalTitleService.findByName(journalTitle.getName()) != null) {
                errors.rejectValue("name", "", "Already exists!");
            }

            if (!journalTitle.getName().equals("") && !PATTERN.matcher(journalTitle.getName()).matches()) {
                errors.rejectValue("name", "", "Category not include digits!");
            }
        }
    }
}
