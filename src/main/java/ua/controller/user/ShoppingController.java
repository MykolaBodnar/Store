package ua.controller.user;

import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.dto.CategoryDto;
import ua.dto.filter.ItemFilter;
import ua.dto.filter.NameFilter;
import ua.service.CategoryService;
import ua.service.ItemService;
import ua.service.OrderService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.List;

@Controller
public class ShoppingController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private OrderService orderService;

    @ModelAttribute("filter")
    public ItemFilter getItemFilter() {
        return new ItemFilter();
    }

    @ModelAttribute("nameFilter")
    public NameFilter getNameFilter() {
        return new NameFilter();
    }

    @RequestMapping("/category")
    public @ResponseBody
    List<CategoryDto> getCategories() {
        return categoryService.findAll();
    }

    @RequestMapping("/category/{id}")
    public String getItems(@PathVariable int id, Model model, @PageableDefault Pageable pageable,
                           @ModelAttribute("filter") ItemFilter filter) {
        model.addAttribute("page", itemService.findAll(filter, pageable, id));
        model.addAttribute("filterParam", itemService.getFilterParam(id));
        return "user-itemList";
    }

    @RequestMapping("/item")
    public String searchItems(@ModelAttribute("nameFilter") @RequestParam String name,
                              @PageableDefault Pageable pageable, Model model) {
        model.addAttribute("page", itemService.findAll(name, pageable));
        return "user-itemSearch";
    }

    @RequestMapping("/item/{id}")
    public String getItem(@PathVariable int id, Model model) {
        model.addAttribute("item", itemService.findOneDto(id));
        return "user-item";
    }

    @RequestMapping("/item/{id}/description")
    public @ResponseBody
    String getDescription(@PathVariable int id) {
        return itemService.getLongDescription(id);
    }

    @RequestMapping(value = "/made-order", method = RequestMethod.POST)
    public String madeOrder(@CookieValue(defaultValue = "", name = "items") String cookie, Principal principal,
                            HttpServletResponse response) {
        deleteCookie(response);
        orderService.save(principal, cookie);
        return "redirect:/";
    }

    private void deleteCookie(HttpServletResponse response) {
        Cookie cookie = new Cookie("items", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    @RequestMapping("/profile")
    public String profile(Principal principal, Model model) {
        model.addAttribute("orders", orderService.findAllByUser(principal));

        return "user-profile";

    }
}
