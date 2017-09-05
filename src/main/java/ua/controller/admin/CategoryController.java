package ua.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import ua.dto.filter.ItemFilter;
import ua.dto.filter.NameFilter;
import ua.entity.Category;
import ua.service.CategoryService;
import ua.service.ItemService;
import ua.service.ProducerService;
import ua.service.AttributeService;
import ua.validator.CategoryValidator;

import javax.validation.Valid;


@Controller
@RequestMapping("/admin/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private AttributeService attributeService;

    @Autowired
    private ProducerService producerService;

    @Autowired
    private ItemService itemService;

    @ModelAttribute("form")
    public Category getForm() {
        return new Category();
    }

    @ModelAttribute("filter")
    public NameFilter filter() {
        return new NameFilter();
    }

    @ModelAttribute("itemFilter")
    public ItemFilter itemFilter() {
        return new ItemFilter();
    }


    @InitBinder("form")
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.setValidator(new CategoryValidator(categoryService));
    }

    @RequestMapping
    public String list(Model model, @ModelAttribute("filter") NameFilter filter, @PageableDefault Pageable pageable) {
        model.addAttribute("page", categoryService.findAll(filter, pageable));
        return "admin-categoryList";
    }

    @PostMapping
    public String save(@ModelAttribute("form") @Valid Category category, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin-categoryForm";
        }
        categoryService.save(category);
        return "redirect:/admin/category";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable int id) {
        categoryService.delete(id);
        return "redirect:/admin/category";
    }

    @RequestMapping(params = "form")
    public String form() {
        return "admin-categoryForm";
    }

    @RequestMapping(value = "/update/{id}")
    public String update(@PathVariable int id, Model model) {
        model.addAttribute("form", categoryService.findOne(id));
        return "admin-categoryForm";
    }

    @RequestMapping(value = "/{id}/attributes")
    public String attributes(Model model, @PathVariable int id, @ModelAttribute("filter") NameFilter filter,
                             @PageableDefault Pageable pageable) {
        Category category = categoryService.findOneWithAttribute(id);
        model.addAttribute("category", category);
        model.addAttribute("page", attributeService
                .findAllWithOutLoaded(category, filter, pageable));
        return "admin-categoryAttributes";
    }

    @RequestMapping("/{id}/attributes/add/{attributeId}")
    public String addAttribute(@PathVariable int id, @PathVariable int attributeId) {
        categoryService.addAttribute(id, attributeId);
        return "redirect:/admin/category/" + id + "/attributes";
    }

    @RequestMapping("/{id}/attributes/delete/{attributeId}")
    public String deleteStringAttribute(@PathVariable int id, @PathVariable int attributeId) {
        categoryService.deleteAttribute(id, attributeId);
        return "redirect:/admin/category/" + id + "/attributes";
    }

    @RequestMapping(value = "/{id}/items")
    public String items(Model model, @PathVariable int id, @ModelAttribute("itemFilter") ItemFilter filter,
                        @PageableDefault Pageable pageable) {
        model.addAttribute("category", categoryService.findOne(id));
        model.addAttribute("producers", producerService.findAllByCategoryId(id));
        model.addAttribute("attributes", attributeService.findAllWithValuesByCategoryId(id));
        model.addAttribute("page", itemService.findAll(filter, pageable, id));
        return "admin-categoryItemsList";
    }


}
