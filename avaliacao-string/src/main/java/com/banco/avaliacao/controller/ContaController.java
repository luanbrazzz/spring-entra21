package com.banco.avaliacao.controller;

import com.banco.avaliacao.model.Cliente;
import com.banco.avaliacao.model.ContaBancaria;
import com.banco.avaliacao.model.Movimentacao;
import com.banco.avaliacao.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ContaController {

    @Autowired
    private ContaService contaService;

    // Criar cliente
    @PostMapping("/clientes")
    public Cliente criarCliente(@RequestBody Cliente cliente) {
        return contaService.criarCliente(cliente);
    }

    // Criar conta para cliente
    @PostMapping("/clientes/{clienteId}/contas")
    public ContaBancaria criarConta(@PathVariable Long clienteId) {
        return contaService.criarConta(clienteId);
    }

    // Consultar saldo
    @GetMapping("/contas/{contaId}/saldo")
    public double consultarSaldo(@PathVariable Long contaId) {
        return contaService.consultarSaldo(contaId);
    }

    // Ver movimentações
    @GetMapping("/contas/{contaId}/movimentacoes")
    public List<Movimentacao> consultarMovimentacoes(@PathVariable Long contaId) {
        return contaService.consultarMovimentacoes(contaId);
    }

    // Ver detalhes da movimentação
    @GetMapping("/contas/{contaId}/movimentacoes/{movimentacaoId}")
    public Movimentacao consultarMovimentacao(@PathVariable Long contaId, @PathVariable Long movimentacaoId) {
        return contaService.consultarMovimentacao(contaId, movimentacaoId);
    }

    // Realizar depósito
    @PostMapping("/contas/{contaId}/deposito")
    public ContaBancaria depositar(@PathVariable Long contaId,
                                   @RequestParam double valor,
                                   @RequestParam(required = false) String descricao) {
        return contaService.depositar(contaId, valor, descricao != null ? descricao : "Depósito");
    }

    // Realizar saque
    @PostMapping("/contas/{contaId}/saque")
    public ContaBancaria sacar(@PathVariable Long contaId,
                               @RequestParam double valor,
                               @RequestParam(required = false) String descricao) {
        return contaService.sacar(contaId, valor, descricao != null ? descricao : "Saque");
    }

    // Atualizar cliente
    @PutMapping("/clientes/{clienteId}")
    public Cliente atualizarCliente(@PathVariable Long clienteId, @RequestBody Cliente novosDados) {
        return contaService.atualizarCliente(clienteId, novosDados);
    }
}
