package ua.controller.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ua.dto.ResetPasswordForm;
import ua.entity.User;
import ua.service.UserService;
import ua.validator.ResetPasswordValidator;
import ua.validator.UserValidator;

import javax.validation.Valid;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @ModelAttribute("form")
    public User getForm() {
        return new User();
    }

    @ModelAttribute("resetForm")
    public ResetPasswordForm getPasswordForm() {
        return new ResetPasswordForm();
    }

    @InitBinder("form")
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.setValidator(new UserValidator(userService));
    }

    @InitBinder("resetForm")
    public void initBinder2(WebDataBinder webDataBinder) {
        webDataBinder.setValidator(new ResetPasswordValidator(userService));
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String save(@Valid @ModelAttribute("form") User user, BindingResult bindingResult,Model model) {
        if (bindingResult.hasErrors()) {
            return "user-registration";
        }
        userService.save(user);
        model.addAttribute("message","You registered, for login confirm your email");
        return "user-message";
    }

    @RequestMapping(value = "/registration")
    public String form() {
        return "user-registration";
    }

    @RequestMapping(value = "/confirm/{uuid}")
    public String confirm(@PathVariable String uuid,Model model) {
        model.addAttribute("message","Your email confirmed");
        userService.confirm(uuid);
        return "user-message";
    }

    @RequestMapping(value = "/forgot-password")
    public String getForgotPasswordForm() {
        return "user-forgotPassword";
    }

    @RequestMapping(value = "/forgot-password", method = RequestMethod.POST)
    public String forgotPassword(@RequestParam String emailForResetPassword,Model model) {
        userService.forgotPassword(emailForResetPassword);
        model.addAttribute("message","New password configuration send on your email");
        return "user-message";
    }

    @RequestMapping(value = "/reset-password/{uuid}")
    public String getResetPasswordForm(Model model, @PathVariable String uuid) {
        model.addAttribute("uuid", uuid);
        return "user-resetPassword";
    }

    @RequestMapping(value = "/reset-password", method = RequestMethod.POST)
    public String resetPassword(@Valid @ModelAttribute("resetForm") ResetPasswordForm resetForm,
                                BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return getResetPasswordForm(model, resetForm.getUuid());
        }
        userService.resetPassword(resetForm);
        return "redirect:/";
    }
}
