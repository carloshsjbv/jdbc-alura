package connection;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.hsqldb.jdbc.JDBCPool;

public class ConnectionPool {

    private final DataSource dataSource;

    public ConnectionPool() {
        JDBCPool pool = new JDBCPool();
        pool.setURL("jdbc:hsqldb:hsql://localhost/loja-virtual");
        pool.setUser("SA");
        pool.setPassword("");
        this.dataSource = pool;
    }

    public Connection getConnection() throws SQLException {
//        Connection connection = DriverManager.getConnection(
//                "jdbc:hsqldb:hsql://localhost/loja-virtual",
//                "SA",
//                ""
//        );
        Connection connection = dataSource.getConnection();
        return connection;
    }

}
