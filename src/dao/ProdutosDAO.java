package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Categoria;
import model.Produto;

public class ProdutosDAO {

    private final Connection con;

    public ProdutosDAO(Connection conexao) {
        this.con = conexao;
    }

    public void save(Produto produto) throws SQLException {

        String sql = "INSERT INTO PRODUTO(NOME, DESCRICAO) VALUES(?, ?)";

        try (PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getDescricao());

            stmt.execute();

            try (ResultSet resultSet = stmt.getGeneratedKeys()) {

                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    produto.setId(id);
                    System.out.println("Produto inserido: " + produto.toString());
                }

            }

        }
    }

    public List<Produto> list() throws SQLException {

        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM PRODUTO";

        try (PreparedStatement statment = con.prepareStatement(sql)) {
            statment.execute();

            transformaResultadoEmObjeto(statment, produtos);

            return produtos;

        }

    }

    public Iterable<Produto> busca(Categoria categoria) throws SQLException {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM PRODUTO WHERE CATEGORIA_ID = ?";

        try (PreparedStatement statment = con.prepareStatement(sql)) {
            statment.setInt(1, categoria.getId());
            statment.execute();

            transformaResultadoEmObjeto(statment, produtos);

            return produtos;

        }
    }

    private void transformaResultadoEmObjeto(PreparedStatement statment, List<Produto> produtos) throws SQLException {
        try (ResultSet resultSet = statment.getResultSet()) {

            while (resultSet.next()) {

                int id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                String descricao = resultSet.getString("descricao");

                Produto produto = new Produto(nome, descricao);
                produto.setId(id);

                produtos.add(produto);

            }

        }
    }

}
