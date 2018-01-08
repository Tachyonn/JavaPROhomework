package ua.kiev.prog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.kiev.prog.model.Contact;
import ua.kiev.prog.model.Group;
import ua.kiev.prog.services.ContactService;
import ua.kiev.prog.services.GroupService;

import java.util.List;

@Controller
public class GroupController {
    static final int DEFAULT_GROUP_ID = -1;

    @Autowired
    private ContactService contactService;
    @Autowired
    private GroupService groupService;

    private String currentGroup = "Default";
    private long currentGroupId = -1L;

    @RequestMapping("/group_add_page")
    public String groupAddPage() {
        return "group_add_page";
    }

    @RequestMapping(value = "/group/add", method = RequestMethod.POST)
    public String groupAdd(@RequestParam String name) {
        groupService.addGroup(new Group(name));
        return "redirect:/";
    }

    @RequestMapping("/group/{id}")
    public String listGroup(@PathVariable(value = "id") long groupId, Model model) {
        Group group = (groupId != DEFAULT_GROUP_ID) ? contactService.findGroup(groupId) : null;

        model.addAttribute("groups", groupService.listGroups());
        model.addAttribute("contacts", contactService.listContacts(group));
        currentGroup = (group != null) ? group.getName() : "Default";
        model.addAttribute("group", currentGroup);
        currentGroupId = (group != null) ? group.getId() : -1;
        //model.addAttribute("groupID", currentGroupId);
        return "index";
    }

    @RequestMapping("/delete_group")
    public String deleteGroup() {
        System.out.println("curr id:" + currentGroupId);
        if (currentGroupId == -1L) {
            return "index";
        }
        Group group = (currentGroupId != DEFAULT_GROUP_ID) ? groupService.findGroup(currentGroupId) : null;
        List<Contact> groupContacts = contactService.listContacts(group);
        if (groupContacts.size() != 0) {
            long[] ids = new long[groupContacts.size()];
            int count = 0;
            for (Contact contact : groupContacts) {
                ids[count++] = contact.getId();
            }
            contactService.deleteContact(ids);
        }


        groupService.delete(group);

        currentGroup = "Default";
        currentGroupId = -1L;


        return "redirect:/";
    }

    public String getCurrentGroup() {
        return currentGroup;
    }

    public Long getCurrentGroupId() {
        return currentGroupId;
    }
}
