package ua.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ua.entity.Producer;
import ua.service.ProducerService;

public class ProducerValidator implements Validator {

    private final ProducerService producerService;

    public ProducerValidator(ProducerService producerService) {
        this.producerService = producerService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(Producer.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Producer producer = (Producer) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "", "Can`t be empty");
        if (producer.getId() == 0 && producerService.findOne(producer.getName()) != null) {
            errors.rejectValue("name", "", "Already exist");
        }
    }
}
