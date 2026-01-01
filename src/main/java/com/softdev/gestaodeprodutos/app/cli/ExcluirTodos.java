package com.softdev.gestaodeprodutos.app.cli;

import com.softdev.gestaodeprodutos.core.Produto;
import com.softdev.gestaodeprodutos.infra.db.ConexaoDB;
import com.softdev.gestaodeprodutos.infra.config.DatabaseConfig;
import com.softdev.gestaodeprodutos.infra.db.ProdutoDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ExcluirTodos {
    public static void main(String[] args) {

        DatabaseConfig databaseConfig = new DatabaseConfig();
        try (Connection conexao = ConexaoDB.conectar(databaseConfig.getUrl(), databaseConfig.getUsername(), databaseConfig.getPassword())) {
            ProdutoDAO produtoDAO = new ProdutoDAO(conexao);

            // Lista todos os produtos
            mostrarProdutos(produtoDAO);

            // Excluir Todos
            produtoDAO.excluirTodos();


            System.out.println("Lista apos excluir todos os produtos");
            mostrarProdutos(produtoDAO);

        } catch (SQLException ex) {
            System.out.println("Erro ao conectar com o banco de dados: " + ex.getMessage());
        }
    }

    // Metodo para listar os produtos
    private static void mostrarProdutos(ProdutoDAO produtoDAO) {
        List<Produto> todosProdutos = produtoDAO.listarTodos();
        if(todosProdutos.isEmpty()){
            System.out.println("Nenhum produto encontrado");
        } else {
            System.out.println("Produtos encontrados:");
            for (Produto produto : todosProdutos) {
                System.out.println(produto.getId() + ":" + produto.getNome() + ":" + produto.getPreco());
            }
        }
    }
}
