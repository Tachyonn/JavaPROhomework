package JDBCDemo;

import java.util.List;

public interface ClientDAO {

    void init();

    //CrUD
    void addClient(String name, int age); //create

    void changeClient(int id, String name, int age); //update

    void deleteClient(int id); //delete

    List<Client> getAll();

    long count();
}
