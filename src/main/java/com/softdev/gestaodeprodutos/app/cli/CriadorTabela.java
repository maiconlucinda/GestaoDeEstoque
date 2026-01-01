package com.softdev.gestaodeprodutos.app.cli;

import com.softdev.gestaodeprodutos.infra.config.DatabaseConfig;
import com.softdev.gestaodeprodutos.infra.db.ConexaoDB;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CriadorTabela {
    public static void main(String[] args) {

        DatabaseConfig databaseConfig = new DatabaseConfig();

        try (Connection conexao = ConexaoDB.conectar(databaseConfig.getUrl(), databaseConfig.getUsername(), databaseConfig.getPassword());
             Statement stmt = conexao.createStatement()) {

        // Definindo o comando SQL para criar a tabela
            String comandoSQL = "CREATE TABLE produtos (" +
                    "id_produto BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY," +
                    "nome_produto TEXT NOT NULL," +
                    "quantidade INTEGER," +
                    "preco REAL," +
                    "status TEXT" +
                    ");";


            System.out.println (comandoSQL);

            // Executando o comando SQL
            stmt.execute (comandoSQL);

            System.out.println ("Tabela 'Produtos' criada com sucesso!");


        } catch (SQLException e) {
            System.err.println ("Erro ao criar a tabela: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
