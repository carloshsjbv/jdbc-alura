
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaListagem {

    public static void main(String[] args) throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/loja-virtual", "SA", "");
        System.out.println("Conexão aberta");

        // Cria "conversa" com o banco de dados
        Statement statement = connection.createStatement();
        // O método execute retorna um boolean. True para quando encontrar algo e false para o contrário
        // boolean resultado = statement.execute("SELECT * FROM PRODUTO");

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
        System.out.println("Conexão fechada");

    }

}
