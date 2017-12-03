package JDBCDemo;

import java.sql.Connection;

public class DAO2Impl extends AbstractDAO<Client> {

    public DAO2Impl(Connection connection, String table) {
        super(connection, table, Client.class);
    }
}
