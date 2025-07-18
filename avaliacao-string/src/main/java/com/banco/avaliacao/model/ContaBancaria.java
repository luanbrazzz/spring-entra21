package com.banco.avaliacao.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ContaBancaria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double saldo;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Movimentacao> movimentacoes = new ArrayList<>();

    // Construtores
    public ContaBancaria() {}

    public ContaBancaria(Cliente cliente) {
        this.cliente = cliente;
        this.saldo = 0.0;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Movimentacao> getMovimentacoes() {
        return movimentacoes;
    }

    public void setMovimentacoes(List<Movimentacao> movimentacoes) {
        this.movimentacoes = movimentacoes;
    }

    // Métodos utilitários
    public void depositar(double valor) {
        this.saldo += valor;
    }

    public void sacar(double valor) {
        this.saldo -= valor;
    }

    public void adicionarMovimentacao(Movimentacao m) {
        this.movimentacoes.add(m);
    }
}
