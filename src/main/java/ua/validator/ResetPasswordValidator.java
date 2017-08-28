package ua.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ua.dto.ResetPasswordForm;
import ua.entity.User;
import ua.service.UserService;

import java.util.regex.Pattern;

public class ResetPasswordValidator implements Validator {
    private final UserService userService;

    public ResetPasswordValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(ResetPasswordForm.class);
    }

    @Override
    public void validate(Object o, Errors errors) {

        ResetPasswordForm resetPasswordForm = (ResetPasswordForm) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "", "Can`t be empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "uuid", "", "uuid can`t be empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "", "Can`t be empty");
        if (resetPasswordForm.getPassword().length() < 6) {
            errors.rejectValue("password", "", "Password must contain more than 5 characters");
        }
        Pattern pattern = Pattern.compile(UserValidator.EMAIL_PATTERN);
        if (!pattern.matcher(resetPasswordForm.getEmail()).matches()) {
            errors.rejectValue("email", "", "Incorrect email");
        } else{
            User user = userService.findOne(resetPasswordForm.getEmail());
            if(user==null){
                errors.rejectValue("email", "", "Email not found");
            } else if(!user.getUUID().equals(resetPasswordForm.getUuid())){
                errors.rejectValue("email", "", "Input email not equals your email");
            }
        }
    }
}
