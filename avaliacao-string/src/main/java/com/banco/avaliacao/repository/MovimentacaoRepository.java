package com.banco.avaliacao.repository;

import com.banco.avaliacao.model.Movimentacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {
}
