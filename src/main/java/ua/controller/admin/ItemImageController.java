package ua.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ua.service.ItemImageService;
import ua.service.ItemService;

@Controller
@RequestMapping("/admin/item/{itemId}/images")
public class ItemImageController {
    @Autowired
    ItemImageService itemImageService;
    @Autowired
    ItemService itemService;

    @PostMapping
    public String save(@RequestParam MultipartFile[] images, @PathVariable int itemId){
        itemImageService.save(images,itemId);
        return "redirect:/admin/item/"+itemId+"/images/";
    }
    @RequestMapping
    public String list(@PathVariable int itemId, Model model){
        model.addAttribute("images",itemImageService.findAllByItemId(itemId));
        return "admin-itemImageList";
    }
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable int id,@PathVariable int itemId){
        itemImageService.delete(id);
        return "redirect:/admin/item/" + itemId + "/images/";
    }
}
