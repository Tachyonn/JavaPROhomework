package ua.kiev.prog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.kiev.prog.controllers.GroupController;
import ua.kiev.prog.services.ContactService;

@Controller
public class RootController {
    private static final int ITEMS_PER_PAGE = 6;

    @Autowired
    private ContactService contactService;
    @Autowired
    private GroupController groupController;

    @RequestMapping("/")
    public String index(Model model, @RequestParam(required = false, defaultValue = "0") Integer page) {
        if (page < 0) page = 0;

        long totalCount = contactService.count();
        int start = page * ITEMS_PER_PAGE;
        long pageCount = (totalCount / ITEMS_PER_PAGE) +
                ((totalCount % ITEMS_PER_PAGE > 0) ? 1 : 0);

        model.addAttribute("groups", contactService.listGroups());
        model.addAttribute("contacts", contactService.listContacts(null, start, ITEMS_PER_PAGE));
        model.addAttribute("pages", pageCount);
        model.addAttribute("group", groupController.getCurrentGroup());

        return "index";
    }
}
