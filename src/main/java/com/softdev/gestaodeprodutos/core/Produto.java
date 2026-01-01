package com.softdev.gestaodeprodutos.core;

public class Produto {
    private int id;
    private String nome;
    private int quantidade;
    private double preco;
    private String status;

    public Produto(String initNome, int initQuantidade, double initPreco, String initStatus) {
        nome = initNome;
        quantidade = initQuantidade;
        preco = initPreco;
        status = initStatus;
    }

    public Produto() {
        // Construtor vazio
    }

    // GETTERS E SETTERS
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
