package com.banco.avaliacao.service;

import com.banco.avaliacao.model.Cliente;
import com.banco.avaliacao.model.ContaBancaria;
import com.banco.avaliacao.model.Movimentacao;
import com.banco.avaliacao.repository.ClienteRepository;
import com.banco.avaliacao.repository.ContaRepository;
import com.banco.avaliacao.repository.MovimentacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContaService {

    @Autowired
    private ClienteRepository clienteRepo;

    @Autowired
    private ContaRepository contaRepo;

    @Autowired
    private MovimentacaoRepository movimentacaoRepo;

    // Criar cliente
    public Cliente criarCliente(Cliente cliente) {
        return clienteRepo.save(cliente);
    }

    // Criar conta para cliente
    public ContaBancaria criarConta(Long clienteId) {
        Cliente cliente = clienteRepo.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        ContaBancaria conta = new ContaBancaria(cliente);
        return contaRepo.save(conta);
    }

    // Ver saldo da conta
    public double consultarSaldo(Long contaId) {
        return buscarConta(contaId).getSaldo();
    }

    // Ver movimentações
    public List<Movimentacao> consultarMovimentacoes(Long contaId) {
        return buscarConta(contaId).getMovimentacoes();
    }

    // Ver detalhes da movimentação
    public Movimentacao consultarMovimentacao(Long contaId, Long movimentacaoId) {
        ContaBancaria conta = buscarConta(contaId);
        return conta.getMovimentacoes().stream()
                .filter(m -> m.getId().equals(movimentacaoId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Movimentação não encontrada"));
    }

    // Depósito
    public ContaBancaria depositar(Long contaId, double valor, String descricao) {
        ContaBancaria conta = buscarConta(contaId);
        conta.depositar(valor);
        Movimentacao m = new Movimentacao("Depósito", valor, descricao);
        movimentacaoRepo.save(m);
        conta.adicionarMovimentacao(m);
        return contaRepo.save(conta);
    }

    // Saque
    public ContaBancaria sacar(Long contaId, double valor, String descricao) {
        ContaBancaria conta = buscarConta(contaId);
        if (valor > conta.getSaldo()) {
            throw new RuntimeException("Saldo insuficiente");
        }
        conta.sacar(valor);
        Movimentacao m = new Movimentacao("Saque", valor, descricao);
        movimentacaoRepo.save(m);
        conta.adicionarMovimentacao(m);
        return contaRepo.save(conta);
    }

    // Alterar dados do cliente
    public Cliente atualizarCliente(Long clienteId, Cliente novosDados) {
        Cliente cliente = clienteRepo.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        cliente.setNome(novosDados.getNome());
        cliente.setCpf(novosDados.getCpf());
        cliente.setEmail(novosDados.getEmail());
        return clienteRepo.save(cliente);
    }

    // Métodos auxiliares
    private ContaBancaria buscarConta(Long contaId) {
        return contaRepo.findById(contaId)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));
    }
}
