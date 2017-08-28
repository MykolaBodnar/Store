package ua.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ua.dto.ItemForm;
import ua.service.ItemService;

public class ItemValidator implements Validator {

    private final ItemService itemService;

    public ItemValidator(ItemService itemService) {
        this.itemService = itemService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(ItemForm.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ItemForm item = (ItemForm) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "", "Can`t be empty");
        if (item.getPrice() == null) {
            errors.rejectValue("price", "", "Can`t be empty");
        } else if (item.getPrice().intValue() <= 0) {
            errors.rejectValue("price", "", "Price must be greater than zero");
        }
        if (item.getId() == 0 && itemService.findOne(item.getName()) != null) {
            errors.rejectValue("name", "", "Already exist");
        }
    }
}
