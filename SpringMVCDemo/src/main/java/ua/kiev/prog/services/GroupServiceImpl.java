package ua.kiev.prog.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.kiev.prog.dao.GroupDAO;
import ua.kiev.prog.model.Group;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupDAO groupDAO;

    @Override
    @Transactional
    public void addGroup(Group group) {
        groupDAO.add(group);
    }

    @Override
    @Transactional
    public List<Group> listGroups() {
        return groupDAO.list();
    }

    @Override
    @Transactional
    public Group findGroup(long id) {
        return groupDAO.findOne(id);
    }

    @Override
    @Transactional
    public void delete(Group group) {
        groupDAO.delete(group);
    }
}
