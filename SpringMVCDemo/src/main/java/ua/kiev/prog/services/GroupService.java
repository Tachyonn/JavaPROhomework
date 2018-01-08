package ua.kiev.prog.services;

import ua.kiev.prog.model.Group;

import java.util.List;

public interface GroupService {
    void addGroup(Group group);

    List<Group> listGroups();

    Group findGroup(long id);

    void delete(Group group);
}
