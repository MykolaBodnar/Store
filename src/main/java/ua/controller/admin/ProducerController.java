package ua.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ua.dto.filter.NameFilter;
import ua.entity.Producer;
import ua.service.ProducerService;
import ua.validator.ProducerValidator;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin/producer")
public class ProducerController {

    @Autowired
    private ProducerService producerService;

    @ModelAttribute("form")
    public Producer getForm() {
        return new Producer();
    }

    @ModelAttribute("filter")
    public NameFilter filter() {
        return new NameFilter();
    }

    @RequestMapping(params = "form")
    public String getProducerForm() {
        return "admin-producerForm";
    }

    @InitBinder("form")
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.setValidator(new ProducerValidator(producerService));
    }

    @RequestMapping
    public String list(Model model, @ModelAttribute("filter") NameFilter filter, @PageableDefault Pageable pageable) {
        model.addAttribute("page", producerService.findAll(filter, pageable));
        return "admin-producerList";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String save(@Valid @ModelAttribute("form") Producer producer, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin-producerForm";
        }
        producerService.save(producer);
        return "redirect:/admin/producer";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        producerService.delete(id);
        return "redirect:/admin/producer";
    }

    @RequestMapping("/update/{id}")
    public String update(@PathVariable int id, Model model) {
        model.addAttribute("form", producerService.findOne(id));
        return "admin-producerForm";
    }
}
