package ua.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ua.entity.User;
import ua.service.UserService;

import java.util.regex.Pattern;

/**
 * Created by Admin on 2/2/2017.
 */
public class UserValidator implements Validator {
    private final UserService userService;
    private final static Pattern PATTERN = Pattern.compile("^[^\\d]+$");
    private final static Pattern EMAIL = Pattern.compile(".*@\\w+\\.\\w+$");

    public UserValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "", "Can't be empty!");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "", "Can't be empty!");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "", "Can't be empty!");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "", "Can't be empty!");
        if (user.getId() == null) {
            if (userService.findByUsername(user.getUsername()) != null) {
                errors.rejectValue("username", "", "User with this login already exists!");
            }
            if (userService.findByEmail(user.getEmail()) != null) {
                errors.rejectValue("email", "", "User with this email already exists!");
            }

            if (!user.getFirstName().equals("") && !PATTERN.matcher(user.getFirstName()).matches()) {
                errors.rejectValue("firstName", "", "Firstname not include digits!");
            }
            if (!user.getLastName().equals("") && !PATTERN.matcher(user.getLastName()).matches()) {
                errors.rejectValue("lastName", "", "Lastname not include digits!");
            }
            if (!user.getEmail().equals("") && !EMAIL.matcher(user.getEmail()).matches()) {
                errors.rejectValue("email", "", "Ooops, this is not email address!");
            }
        }


    }
}
