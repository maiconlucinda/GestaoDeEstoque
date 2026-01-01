package com.softdev.gestaodeprodutos.infra.db;

import com.softdev.gestaodeprodutos.core.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
 * ProdutoDAO (Data Access Object)
 * Classe responsavel pelo acesso aos Dados no Banco de dados
 */
public class ProdutoDAO {

    private final Connection CONEXAO_DB;
    // Construtor
    public ProdutoDAO(Connection conexao) {
        CONEXAO_DB = conexao;
    }

    //Metoodo para inserir um novo produto no banco de dados
    public void inserir(Produto produto) {
        String sql = "INSERT INTO produtos (nome_produto, quantidade, preco, status) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = CONEXAO_DB.prepareStatement(sql)) {
            stmt.setString(1, produto.getNome());
            stmt.setInt(2, produto.getQuantidade());
            stmt.setDouble(3, produto.getPreco());
            stmt.setString(4, produto.getStatus());
            stmt.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro ao inserir o produto: " + ex.getMessage());
        }
    }

    // Metodo para excluir todos os produtos
    public void excluirTodos() {

        // Cria a query
        String sql = "DELETE FROM produtos";

        // Passa a query para o banco de dados, banco de dados gera plano de execucao
        try (PreparedStatement stmt = CONEXAO_DB.prepareStatement(sql)) {
            // Executa a acao
            stmt.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro ao deletar todos os produtos: " + ex.getMessage());
        }
    }

    // Metoodo para consultar item por id
    public Produto consultarPorId(int id) {
        String sql = "SELECT * FROM produtos WHERE id_produto = ?";

        try (PreparedStatement smtm = CONEXAO_DB.prepareStatement(sql)) {

            // Preparo a query
            smtm.setInt(1, id);

            // Executao a query
            try (ResultSet resultado = smtm.executeQuery()) { // Retorna um objeto criado com apos a consulta no banco
                if (resultado.next()) { // Caso a consulta encontre pelo menos uma linha, poderei iterar com o resultado, caos encontre mais, poderei iterar multiplas vezes.
                    // Criando objeto vazio
                    Produto produto = new Produto();

                    // Atribuo os resultados ao meu objeto criado
                    produto.setId(resultado.getInt("id_produto"));
                    produto.setNome(resultado.getString("nome_produto"));
                    produto.setQuantidade(resultado.getInt("quantidade"));
                    produto.setPreco(resultado.getDouble("preco"));
                    return produto;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao consultar o produto: " + e.getMessage());
        }
        // Preciso ter esse return null porque o metoodo promete que vai retornar algo, caso nao tenha nada no banco de dados, preciso retornar algo aqui no final
        return null;
    }


    // Metoodo para atualizar um produto no banco de dados
    public void atualizar(Produto produto) {
        String sql = "UPDATE produtos SET nome_produto = ?, quantidade = ?, status = ?, preco = ? WHERE id_produto = ?";

        try (PreparedStatement stmt = CONEXAO_DB.prepareStatement(sql)) {
            stmt.setString(1, produto.getNome());
            stmt.setInt(2, produto.getQuantidade());
            stmt.setString(3, produto.getStatus());
            stmt.setDouble(4, produto.getPreco());

            // Pego o ID do produto acessando o objeto tambem
            stmt.setInt(5, produto.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar o produto: " + e.getMessage());
        }
    }

    // Metodo para excluir um produto pelo seu ID
    public void excluir(int id) {
        String sql = "DELETE FROM produtos WHERE id_produto = ?";

        try (PreparedStatement stmt = CONEXAO_DB.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Erro ao deletar produto " + e.getMessage());
        }
    }

    // Metodo para listar todos os produtos do banco de dados
    public List<Produto> listarTodos() {
        String sql = "SELECT * FROM produtos";

        // Crio uma lista de Produtos (ArrayList)
        List<Produto> produtos = new ArrayList<>();
        try (PreparedStatement stmt = CONEXAO_DB.prepareStatement(sql)) {
            ResultSet resultado = stmt.executeQuery();

            while (resultado.next()) {

                // Instanciando um novo produto
                Produto produto = new Produto();
                produto.setId(resultado.getInt("id_produto"));
                produto.setNome(resultado.getString("nome_produto"));
                produto.setQuantidade(resultado.getInt("quantidade"));
                produto.setPreco(resultado.getDouble("preco"));
                produto.setStatus(resultado.getString("status"));

                // Adicionando o objeto produto a lista de produtos
                produtos.add(produto);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar todos os produtos: " + e.getMessage());
        }
        return produtos;
    }

}
