package com.softdev.gestaodeprodutos.infra.config;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class DatabaseConfig {

    private final Properties props =  new Properties();

    public DatabaseConfig() {

        try (InputStream input = Files.newInputStream(Path.of("config/application-local.properties"))) {
            props.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Erro para obter as credenciais do banco de dados: " + e.getMessage());
        }
    }


    public String getUrl() {
        return props.getProperty("db.url");
    }

    public String getUsername() {
        return props.getProperty("db.user");
    }

    public String getPassword() {
        return props.getProperty("db.password");
    }
}