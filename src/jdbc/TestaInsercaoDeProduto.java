package jdbc;

import connection.ConnectionPool;
import dao.ProdutosDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import model.Produto;

public class TestaInsercaoDeProduto {

    public static void main(String[] args) throws SQLException {

        try (Connection conexao = new ConnectionPool().getConnection()) {

            Produto produto = new Produto("Monitor AOC", "Monitor 19");

            ProdutosDAO produtosDAO = new ProdutosDAO(conexao);
            produtosDAO.save(produto);

            List<Produto> retornoProdutos = produtosDAO.list();
            for (Produto itemProduto : retornoProdutos) {
                System.out.println("Itens do banco: " + itemProduto.toString());
            }

        }

    }

}
