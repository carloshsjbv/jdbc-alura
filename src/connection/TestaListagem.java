package connection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaListagem extends ConnectionPool {

    public static void main(String[] args) throws SQLException {
        Connection connection = new ConnectionPool().getConnection();

        Statement statement = connection.createStatement();
        boolean retorno = statement.execute("SELECT * FROM PRODUTO");

        ResultSet resultSet = statement.getResultSet();

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            System.out.println(id);
            String nome = resultSet.getString("nome");
            System.out.println(nome);
            String descricao = resultSet.getString("descricao");
            System.out.println(descricao);
        }
        resultSet.close();
        statement.close();
        connection.close();

    }

}
