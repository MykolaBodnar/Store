package ua.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ua.dto.filter.AttributeValueFilter;
import ua.dto.filter.NameFilter;
import ua.entity.Attribute;
import ua.service.AttributeService;
import ua.service.AttributeValueService;
import ua.validator.AttributeValidator;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin/attribute")
public class AttributeController {
    @Autowired
    private AttributeService attributeService;

    @Autowired
    private AttributeValueService attributeValueService;

    @ModelAttribute("form")
    public Attribute getForm() {
        return new Attribute();
    }

    @ModelAttribute("filter")
    public NameFilter filter() {
        return new NameFilter();
    }

    @ModelAttribute("valueFilter")
    public AttributeValueFilter getAttributeValueFilter() {
        return new AttributeValueFilter();
    }

    @InitBinder("form")
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.setValidator(new AttributeValidator(attributeService));
    }

    @RequestMapping
    public String list(Model model, @ModelAttribute("filter") NameFilter filter, @PageableDefault Pageable pageable) {
        model.addAttribute("page", attributeService.findAll(filter, pageable));
        return "admin-attributeList";
    }

    @RequestMapping(params = "form")
    public String form() {
        return "admin-attributeForm";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String save(@Valid @ModelAttribute("form") Attribute attribute, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin-attributeForm";
        }
        attributeService.save(attribute);
        return "redirect:/admin/attribute";
    }

    @RequestMapping(value = "/update/{id}")
    public String update(@PathVariable int id, Model model) {
        model.addAttribute("form", attributeService.findOne(id));
        return "admin-attributeForm";
    }

    @RequestMapping(value = "/delete/{id}")
    public String delete(@PathVariable int id) {
        attributeService.delete(id);
        return "redirect:/admin/attribute";
    }

    @RequestMapping(value = "/{id}/values")
    public String list(Model model, @PathVariable int id, @PageableDefault Pageable pageable,
                       @ModelAttribute("valueFilter") AttributeValueFilter filter) {
        model.addAttribute("page", attributeValueService.findAll(id, filter, pageable));
        model.addAttribute("attributeId", id);
        return "admin-attributeValueList";

    }
}
