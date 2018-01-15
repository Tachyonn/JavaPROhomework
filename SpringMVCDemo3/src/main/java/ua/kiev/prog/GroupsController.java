package ua.kiev.prog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class GroupsController {
    @Autowired
    private ContactService contactService;

    @RequestMapping("/groups")
    public String groups(Model model, @RequestParam(required = false, defaultValue = "0") Integer pageNum) {
        if (pageNum < 0) pageNum = 0;
        List<Group> groups = contactService
                .findGroups(new PageRequest(pageNum, MyController.ITEMS_PER_PAGE, Sort.Direction.DESC, "id"));

        model.addAttribute("groups", getGroupsMap(groups));
        model.addAttribute("allPages", getGroupCount());
        return "groups";
    }

    private Map<Group, Long> getGroupsMap(List<Group> groups) {
        Map<Group, Long> groupsMap = new HashMap<>();
        for (Group group : groups) {
            groupsMap.put(group, contactService.countByGroup(group));
        }
        return groupsMap;
    }

    @RequestMapping("/groups/delwi")
    public ResponseEntity<Void> delWithContacts(@RequestParam(value = "toDelete[]", required = false) long[] toDelete) {
        if (toDelete != null && toDelete.length > 0) {
            contactService.deleteGroup(toDelete);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping("/groups/delwo")
    public ResponseEntity<Void> delWithOutContacts(@RequestParam(value = "toDelete[]", required = false) long[] toDelete) {
        if (toDelete != null && toDelete.length > 0)
            for (long id : toDelete) {
                Group group = contactService.findGroup(id);
                List<Contact> contacts = contactService.findByGroup(group);
                for (Contact contact : contacts) {
                    contact.setGroup(null); // разрываем связи
                }
                group.setContacts(null); // разрываем связи
                contactService.deleteGroup(group);
            }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private long getGroupCount() {
        long totalCount = contactService.countGroup();
        return (totalCount / MyController.ITEMS_PER_PAGE) + ((totalCount % MyController.ITEMS_PER_PAGE > 0) ? 1 : 0);
    }
}
