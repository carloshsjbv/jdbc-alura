package connection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaRemocao {

    public static void main(String[] args) throws SQLException {

        Connection connection = new ConnectionPool().getConnection();

        Statement statement = connection.createStatement();
        boolean result = statement.execute("DELETE FROM PRODUTO WHERE ID > 3");

        System.out.println(statement.getUpdateCount() + " linhas atualizadas");

        statement.close();
        connection.close();

    }

}
