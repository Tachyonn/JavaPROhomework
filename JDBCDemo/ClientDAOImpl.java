package JDBCDemo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDAOImpl implements ClientDAO {
    private Connection conn;

    public ClientDAOImpl(Connection conn) {
        this.conn = conn;
    }

    public void init() {
        try {
            Statement st = conn.createStatement();
            try {
                st.execute("DROP TABLE IF EXISTS Clients");
                st.execute("CREATE TABLE Clients (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, name VARCHAR(20) NOT NULL, age INT)");
            } finally {
                st.close();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addClient(String name, int age) {
        try {
            try (PreparedStatement st = conn.
                    prepareStatement("INSERT INTO Clients (name,age) VALUES (?,?)")) {
                st.setString(1, name);
                st.setInt(2, age);
                st.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteClient(int id) {

    }

    @Override
    public List<Client> getAll() {
        List<Client> result = new ArrayList<>();
        try (Statement st = conn.createStatement()) {
            try (ResultSet rs = st.executeQuery("SELECT * FROM Clients")) {
                while (rs.next()) {
                    Client client = new Client();

                    client.setId(rs.getInt(1));
                    client.setName(rs.getString(2));
                    client.setAge(rs.getInt(3));

                    result.add(client);

                }
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public long count() {
        try (Statement st = conn.createStatement()) {
            try (ResultSet set = st.executeQuery("SELECT COUNT (*) FROM Clients")) {
                if (set.next()) {
                    return set.getLong(1);
                } else {
                    throw new RuntimeException("CountFailed");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public void changeClient(int id, String name, int age) {

        try (Statement st = conn.createStatement()) {

            st.execute("UPDATE Clients set name=\"" + name + "\", age=" + age + "WHERE id=" + id + ";");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
