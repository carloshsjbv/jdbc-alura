
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestaConexao {

    public static void main(String[] args) throws SQLException {
        // Abre conexão com o banco
        Connection connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/loja-virtual");
        System.out.println("Abrindo conexão com sucesso");
        connection.close();
    }

}
