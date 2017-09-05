package ua.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ua.dto.ItemForm;
import ua.editor.CategoryEditor;
import ua.editor.ProducerEditor;
import ua.editor.AttributeValueEditor;
import ua.entity.Category;
import ua.entity.Producer;
import ua.entity.AttributeValue;
import ua.service.*;
import ua.validator.ItemValidator;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private AttributeService attributeService;

    @Autowired
    private AttributeValueService attributeValueService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProducerService producerService;

    @Autowired
    private ItemImageService itemImageService;

    @InitBinder("form")
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(AttributeValue.class,
                new AttributeValueEditor(attributeValueService));
        webDataBinder.registerCustomEditor(Category.class, new CategoryEditor(categoryService));
        webDataBinder.registerCustomEditor(Producer.class, new ProducerEditor(producerService));
        webDataBinder.setValidator(new ItemValidator(itemService));
    }

    @ModelAttribute("form")
    public ItemForm getForm() {
        return new ItemForm();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String save(@Valid @ModelAttribute("form") ItemForm itemForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return itemForm(model, itemForm.getCategory().getId());
        }
        itemService.save(itemForm);
        return "redirect:/admin/item/" + itemForm.getId();
    }

    @RequestMapping(value = "/add-to-category/{id}")
    public String itemForm(Model model, @PathVariable int id) {
        model.addAttribute("category", categoryService.findOne(id));
        model.addAttribute("attributes", attributeService.findAllWithValuesByCategoryId(id));
        model.addAttribute("producers", producerService.findAll());
        return "admin-itemForm";
    }

    @RequestMapping(value = "/delete/{id}")
    public String delete(@PathVariable int id) {
        itemService.delete(id);
        return "redirect:/admin/category";
    }

    @RequestMapping(value = "/update/{id}")
    public String update(@PathVariable int id, Model model) {
        ItemForm itemForm = itemService.findOneForm(id);
        int categoryId = itemForm.getCategory().getId();
        model.addAttribute("category", categoryService.findOne(categoryId));
        model.addAttribute("attributes", attributeService.findAllWithValuesByCategoryId(categoryId));
        model.addAttribute("producers", producerService.findAll());
        model.addAttribute("form", itemForm);
        return "admin-itemForm";
    }

    @RequestMapping(value = "/{id}/longDescription")
    public String longDescription(@PathVariable int id, Model model) {
        model.addAttribute("description", itemService.getLongDescription(id));
        return "admin-itemLongDescription";
    }

    @RequestMapping(value = "/{id}/longDescription", method = RequestMethod.POST)
    public String saveLongDescription(@PathVariable int id, @RequestParam String description) {
        itemService.saveLongDescription(description, id);
        return "redirect:/admin/item/{id}";
    }

    @RequestMapping(value = "/{id}")
    public String getItem(@PathVariable int id, Model model) {
        model.addAttribute("item", itemService.findOneDto(id));
        model.addAttribute("description", itemService.getLongDescription(id));
        return "admin-item";
    }

}
