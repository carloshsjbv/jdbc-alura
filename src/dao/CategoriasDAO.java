package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Categoria;
import model.Produto;

public class CategoriasDAO {

    private final Connection connection;

    public CategoriasDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Categoria> list() throws SQLException {
        String sqlQuery = "SELECT * FROM CATEGORIA";
        List<Categoria> categorias = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.execute();

            try (ResultSet resultSet = statement.getResultSet()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String nome = resultSet.getString("nome");

                    categorias.add(new Categoria(id, nome));
                }
            }
        }

        return categorias;
    }

    public List<Categoria> listComProdutos() throws SQLException {
//        String sqlQuery = "SELECT * FROM CATEGORIA AS c JOIN PRODUTO AS p "
//                + " ON P.CATEGORIA_ID = C.ID";

        List<Categoria> categorias = new ArrayList<>();
        Categoria ultima = null;

        String sqlQuery = "select c.id as c_id, "
                + "c.nome as c_nome, "
                + "p.id as p_id, "
                + "p.nome as p_nome, "
                + "p.descricao as p_descricao "
                + "from Categoria as c join Produto as p "
                + "on p.categoria_id = c.id";

        try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.execute();

            try (ResultSet resultSet = statement.getResultSet()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("c_id");
                    String nome = resultSet.getString("c_nome");

                    if (ultima == null || !ultima.getNome().equals(nome)) {
                        Categoria categoria = new Categoria(id, nome);

                        categorias.add(categoria);

                        ultima = categoria;
                    }
                    int idDoProduto = resultSet.getInt("p_id");
                    String nomeProduto = resultSet.getString("p_nome");
                    String descricaoProduto = resultSet.getString("p_descricao");

                    Produto p = new Produto(nomeProduto, descricaoProduto);
                    p.setId(idDoProduto);
                    ultima.adiciona(p);
                }
            }
        }

        return categorias;
    }

}
