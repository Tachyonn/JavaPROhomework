package JDBCDemo;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    private String url;
    private String login;
    private String password;

    public ConnectionFactory() {
    }

    public ConnectionFactory(String url, String login, String password) {
        this.url = url;
        this.login = login;
        this.password = password;
    }

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(url, login, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Connection load(String filename) {
        Properties connProperties = new Properties();

        try {
            connProperties.load(new FileReader(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.url = connProperties.getProperty("DB_CONNECTION");
        this.login = connProperties.getProperty("DB_USER");
        this.password = connProperties.getProperty("DB_PASSWORD");
        return getConnection();
    }
}
