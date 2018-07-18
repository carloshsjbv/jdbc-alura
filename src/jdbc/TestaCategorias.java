package jdbc;

import connection.ConnectionPool;
import dao.CategoriasDAO;
import model.Categoria;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import model.Produto;

public class TestaCategorias {

    public static void main(String[] args) throws SQLException {

        try (Connection connection = new ConnectionPool().getConnection()) {
            List<Categoria> categorias = new CategoriasDAO(connection).listComProdutos();

            for (Categoria categoria : categorias) {
                System.out.println("Categoria Itens: " + categoria.getNome());

                for (Produto produto : categoria.getProdutos()) {
                    System.out.println(categoria.getNome() + " " + produto.getNome());
                }
            }

        }

    }

}
