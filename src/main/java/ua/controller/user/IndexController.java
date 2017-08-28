package ua.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String homePage(){
        return "user-index";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String loginProcessing() {
        return "redirect:/";
    }

    @RequestMapping(value = "/login-page")
    public String loginPage(){
        return "user-loginPage";
    }



}
