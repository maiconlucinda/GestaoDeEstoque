package com.softdev.gestaodeprodutos.app.cli;

import com.softdev.gestaodeprodutos.core.Produto;
import com.softdev.gestaodeprodutos.infra.db.ConexaoDB;
import com.softdev.gestaodeprodutos.infra.config.DatabaseConfig;
import com.softdev.gestaodeprodutos.infra.db.ProdutoDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class InserirProduto {
    public static void main(String[] args) {

        DatabaseConfig databaseConfig = new DatabaseConfig();
        try (Connection conexao = ConexaoDB.conectar(databaseConfig.getUrl(), databaseConfig.getUsername(), databaseConfig.getPassword())) {
            ProdutoDAO produtoDAO = new ProdutoDAO(conexao);

            // Listar todos os produtos
            mostrarProdutos(produtoDAO);


            // Exemplo de insercao de produto
            Produto novoProduto1 = new Produto("Notebook", 40, 3999.99, "Em estoque");
            Produto novoProduto2 = new Produto("SmartPhone", 20, 1499.99, "Estoque Baixo");
            Produto novoProduto3 = new Produto("Tablet", 20, 799.99, "Estoque Baixo");

            produtoDAO.inserir(novoProduto1);
            produtoDAO.inserir(novoProduto2);
            produtoDAO.inserir(novoProduto3);


            // Lista todos os produtos apos a insercao
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
