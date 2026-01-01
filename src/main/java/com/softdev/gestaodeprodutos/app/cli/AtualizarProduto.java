package com.softdev.gestaodeprodutos.app.cli;

import com.softdev.gestaodeprodutos.core.Produto;
import com.softdev.gestaodeprodutos.infra.db.ConexaoDB;
import com.softdev.gestaodeprodutos.infra.config.DatabaseConfig;
import com.softdev.gestaodeprodutos.infra.db.ProdutoDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class AtualizarProduto {
    public static void main(String[] args) {

        DatabaseConfig databaseConfig = new DatabaseConfig();
        try (Connection conexao = ConexaoDB.conectar(databaseConfig.getUrl(), databaseConfig.getUsername(), databaseConfig.getPassword())) {
            ProdutoDAO produtoDAO = new ProdutoDAO(conexao);

            // Lista todos os produtos
            mostrarProdutos(produtoDAO);

            // Atualizar produto
            Produto produtoConsultado = produtoDAO.consultarPorId(1);
            if(produtoConsultado != null){
                produtoConsultado.setNome("Laptop");
                System.out.println("Novo nome do Produto: " + produtoConsultado.getNome());
                produtoDAO.atualizar(produtoConsultado);

                System.out.println("A base de dados atualizados com sucesso!");
                mostrarProdutos(produtoDAO);

            } else {
                System.out.println("Produto nao encontrado");
            }


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
