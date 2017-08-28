package ua.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ua.entity.AttributeValue;
import ua.service.AttributeValueService;

public class AttributeValueValidator implements Validator {
    private final AttributeValueService attributeValueService;

    public AttributeValueValidator(AttributeValueService attributeValueService) {
        this.attributeValueService = attributeValueService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(AttributeValue.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "value", "", "Can`t be empty");

    }
}
