package ua.kiev.prog.dao;

import ua.kiev.prog.entity.Client;

import java.util.List;

public interface ClientDAO {
    void add(String name, int age);

    List<Client> selectAll();

    void delete(Long id);
}
