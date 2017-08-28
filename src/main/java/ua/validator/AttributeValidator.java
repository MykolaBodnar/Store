package ua.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ua.entity.Attribute;
import ua.service.AttributeService;

public class AttributeValidator implements Validator {
    private final AttributeService attributeService;

    public AttributeValidator(AttributeService attributeService) {
        this.attributeService = attributeService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(Attribute.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Attribute attribute = (Attribute) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "", "Can`t be empty");
        if (attribute.getId() == 0 && attributeService.findOne(attribute.getName()) != null) {
            errors.rejectValue("name", "", "Already exist");
        }
    }
}
