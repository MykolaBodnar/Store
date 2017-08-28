package ua.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ua.entity.User;
import ua.service.UserService;

import java.util.regex.Pattern;

public class UserValidator implements Validator {
    public static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private final UserService userService;

    public UserValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(User.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "", "Can`t be empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "", "Can`t be empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "", "Can`t be empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "", "Can`t be empty");
        if (user.getPassword().length() < 6) {
            errors.rejectValue("password", "", "Password must contain more than 5 characters");
        }
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        if (!pattern.matcher(user.getEmail()).matches()) {
            errors.rejectValue("email", "", "Incorrect email");
        }
        if (userService.findOne(user.getEmail()) != null) {
            errors.rejectValue("email", "", "Already exist");
        }
    }
}
