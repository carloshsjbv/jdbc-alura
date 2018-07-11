package connection;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionTest {

    public static void main(String[] args) throws SQLException {
        try (Connection connection = DriverManager.getConnection(
                "jdbc:hsqldb:hsql://localhost/loja-virtual",
                "SA",
                ""
        )) {

            System.out.println("Conectado!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
