package ua.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ua.dto.AttributeValueDto;
import ua.dto.filter.AttributeValueFilter;
import ua.editor.AttributeEditor;
import ua.entity.Attribute;
import ua.entity.AttributeValue;
import ua.service.AttributeService;
import ua.service.AttributeValueService;
import ua.validator.AttributeValueValidator;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin/attribute-value")
public class AttributeValueController {

    @Autowired
    private AttributeValueService attributeValueService;
    @Autowired
    private AttributeService attributeService;

    @ModelAttribute("form")
    public AttributeValue getForm() {
        return new AttributeValue();
    }

    @InitBinder("form")
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.setValidator(new AttributeValueValidator(attributeValueService));
        webDataBinder.registerCustomEditor(Attribute.class,
                new AttributeEditor(attributeService));

    }

    @RequestMapping(value = "/add-to-attribute/{attributeId}")
    public String form(Model model, @PathVariable int attributeId) {
        model.addAttribute("attributeId", attributeId);
        return "admin-attributeValueForm";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String save(@Valid @ModelAttribute("form") AttributeValue attributeValue, BindingResult bindingResult,
                       Model model) {
        if (bindingResult.hasErrors()) {
            return form(model, attributeValue.getAttribute().getId());
        }
        attributeValueService.save(attributeValue);
        return "redirect:/admin/attribute/" + attributeValue.getAttribute().getId() + "/values";
    }

    @RequestMapping(method = RequestMethod.POST, params = "async")
    public @ResponseBody
    AttributeValueDto saveAsync(@RequestBody AttributeValue attributeValue) {
        attributeValueService.save(attributeValue);
        return new AttributeValueDto(attributeValue.getId(), attributeValue.getValue());
    }

    @RequestMapping(value = "/update/{id}")
    public String update(@PathVariable int id, Model model) {
        AttributeValue attributeValue = attributeValueService.findOneWithAttribute(id);
        model.addAttribute("form", attributeValue);
        model.addAttribute("attributeId", attributeValue.getAttribute().getId());
        return "admin-attributeValueForm";
    }

    @RequestMapping(value = "/delete/{id}")
    public String delete(@PathVariable int id) {
        attributeValueService.delete(id);
        return "redirect:/admin/attribute";
    }
}
