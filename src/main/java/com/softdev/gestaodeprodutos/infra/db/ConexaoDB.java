package com.softdev.gestaodeprodutos.infra.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConexaoDB {

    public static Connection conectar(String url, String usuario, String senha) {
        try {
            return DriverManager.getConnection(url, usuario, senha);
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            return null;
        }

    }
}
