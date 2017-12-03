package JDBCDemo;

import java.sql.Connection;

public class TestJDBC {
    public static void execute(){
        ConnectionFactory cf = new ConnectionFactory();
        //Plain DAO Implementaion
        //ClientDAO dao = new ClientDAOImpl(cf.load("E:/Dropbox/Workspace/JDBCDemo/conn.ini"));
        //new LogicLayer(dao).menuStart();

        //Generic DAO Implementation
        Connection connection = cf.load("E:/Dropbox/Workspace/JDBCDemo/conn.ini");
        DAO2Impl dao2 = new DAO2Impl(connection, "Clients");

        Client client1 = new Client();

        client1.setId(2);
        client1.setName("Petya");
        client1.setAge(22);
        dao2.add(client1); //add DTO into table
        dao2.getAll().forEach(System.out::println); //get all DTO from table

        dao2.delete(client1); // delete DTO from table
        dao2.getAll().forEach(System.out::println);

        dao2.add(client1);
        client1.setName("fedor");
        client1.setAge(48);
        //dao2.getAll().forEach(System.out::println);
        dao2.update(client1); // update existing DTO
        dao2.getAll().forEach(System.out::println);
    }
}
