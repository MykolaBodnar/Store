package ua.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.service.UserService;

@Controller("adminUserController")
@RequestMapping("/admin/user")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping
    public String list(Model model){
        model.addAttribute("users",userService.findAll());
        return "admin-userList";
    }
}
