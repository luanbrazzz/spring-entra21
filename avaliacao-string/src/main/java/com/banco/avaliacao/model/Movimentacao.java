package com.banco.avaliacao.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Movimentacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipo; // "Depósito" ou "Saque"
    private double valor;
    private LocalDate data;
    private String descricao;

    // Construtores
    public Movimentacao() {}

    public Movimentacao(String tipo, double valor, String descricao) {
        this.tipo = tipo;
        this.valor = valor;
        this.descricao = descricao;
        this.data = LocalDate.now(); // define data atual automaticamente
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
