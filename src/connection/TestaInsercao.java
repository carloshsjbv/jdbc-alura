package connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;

public class TestaInsercao {

    public static void main(String[] args) throws SQLException {

        // Certifica-se que a conexão será aberta e fechada
        try (Connection connection = Database.getConnection()) {

            connection.setAutoCommit(false);

            //Statement statement = connection.createStatement();
            String query = "INSERT INTO PRODUTO (NOME, DESCRICAO) VALUES(?, ?)";

            try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {// Obter chaves (ids) gerados no insert

                adiciona("TV LCD", "32 Polegadas", statement);
                adiciona("Blueray", "Blueray HDMI", statement);
                connection.commit();

            } catch (Exception e) {
                connection.rollback();
                System.out.println("Rollback efetuado");
            }

        } catch (Exception ex) {
            System.out.println("Problemas ao conectar");
        }
    }

    public static void adiciona(String nome, String descricao, PreparedStatement statement) throws SQLException {

        if (nome.equals("Blueray")) {
            throw new IllegalArgumentException();
        }

        statement.setString(1, nome);
        statement.setString(2, descricao);

        boolean resultado = statement.execute();
        System.out.println(resultado);

        ResultSet resultSet = statement.getGeneratedKeys();

        while (resultSet.next()) {
            System.out.println(resultSet.getString("id") + " gerado");
        }

        resultSet.close();

    }

}
