package ua.kiev.prog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ContactService {
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private GroupRepository groupRepository;

    @Transactional
    public void addContact(Contact contact) {
        contactRepository.save(contact);
    }

    @Transactional
    public void updateContact(Contact contact) {
        contactRepository.save(contact);
    }

    @Transactional
    public void updateGroup(Group group) {
        groupRepository.save(group);
    }

    @Transactional
    public void addGroup(Group group) {
        groupRepository.save(group);
    }

    @Transactional
    public void deleteContacts(long[] idList) {
        for (long id : idList)
            contactRepository.delete(id);
    }

    @Transactional
    public void deleteContact(Contact contact) {
        contactRepository.delete(contact);
    }

    @Transactional
    public void deleteGroup(Group group) {
        groupRepository.delete(group);
    }

    @Transactional
    public void deleteGroup(long[] idList) {
        for (long id : idList)
            groupRepository.delete(id);
    }

    @Transactional(readOnly = true)
    public List<Group> findGroups(Pageable pageable) {
        return groupRepository.findAll(pageable).getContent();
    }

    @Transactional(readOnly = true)
    public List<Group> findGroups() {
        return groupRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Contact> findAll(Pageable pageable) {
        return contactRepository.findAll(pageable).getContent();
    }

    @Transactional(readOnly = true)
    public List<Contact> findByGroup(Group group, Pageable pageable) {
        return contactRepository.findByGroup(group, pageable);
    }

    @Transactional(readOnly = true)
    public List<Contact> findByGroup(Group group) {
        return contactRepository.findByGroup(group);
    }

    @Transactional(readOnly = true)
    public long countByGroup(Group group) {
        return contactRepository.countByGroup(group);
    }

    @Transactional(readOnly = true)
    public List<Contact> findByPattern(String pattern, Pageable pageable) {
        return contactRepository.findByPattern(pattern, pageable);
    }

    @Transactional(readOnly = true)
    public long count() {
        return contactRepository.count();
    }

    @Transactional(readOnly = true)
    public Group findGroup(long id) {
        return groupRepository.findOne(id);
    }

    @Transactional(readOnly = true)
    public long countGroup() {
        return groupRepository.count();
    }
}
