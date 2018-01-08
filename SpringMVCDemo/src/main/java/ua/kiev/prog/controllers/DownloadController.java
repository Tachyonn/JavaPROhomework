package ua.kiev.prog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.kiev.prog.model.Contact;
import ua.kiev.prog.model.Group;
import ua.kiev.prog.services.ContactService;
import ua.kiev.prog.services.GroupService;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@Controller
public class DownloadController {
    private String filename = "downloads/testfile.csv";
    private final String CSV_SEPARATOR = ",";
    @Autowired
    private GroupController groupController;
    @Autowired
    GroupService groupService;
    @Autowired
    private ContactService contactService;

    @RequestMapping(value = "/download")
    public void downloadCSV(HttpServletResponse response) throws IOException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        File file = new File(classloader.getResource(filename).getFile());

        if (!file.exists()) {
            System.out.println("File not exist!");
            System.out.println(file.getAbsolutePath());
            file.createNewFile();
            System.out.println("New file created!");
        }

        makeCSV(file);

        response.setContentType("text/csv");
        response.setHeader("Content-Disposition",
                String.format("attachment; filename=\"%s\"", file.getName()));
        response.setContentLength((int) file.length());
        InputStream is = new BufferedInputStream(new FileInputStream(file));
        FileCopyUtils.copy(is, response.getOutputStream());
    }

    private void makeCSV(File file) throws IOException {
        Long groupID = groupController.getCurrentGroupId();
        Group group = groupService.findGroup(groupID);
        List<Contact> contacts = contactService.listContacts(group);

        FileWriter fw = new FileWriter(file);
        fw.append("id").append(CSV_SEPARATOR);
        fw.append("name").append(CSV_SEPARATOR);
        fw.append("surname").append(CSV_SEPARATOR);
        fw.append("phone").append(CSV_SEPARATOR);
        fw.append("email").append(CSV_SEPARATOR);
        fw.append("group");
        fw.append(System.lineSeparator());
        for (Contact contact : contacts) {
            fw.append(contact.getId() + "").append(CSV_SEPARATOR);
            fw.append(contact.getName()).append(CSV_SEPARATOR);
            fw.append(contact.getSurname()).append(CSV_SEPARATOR);
            fw.append(contact.getPhone()).append(CSV_SEPARATOR);
            fw.append(contact.getEmail()).append(CSV_SEPARATOR);
            String groupName = contact.getGroup() != null ? contact.getGroup().toString() : "Default";
            fw.append(groupName);
            fw.append(System.lineSeparator());
        }
        fw.flush();
        fw.close();
    }

}
